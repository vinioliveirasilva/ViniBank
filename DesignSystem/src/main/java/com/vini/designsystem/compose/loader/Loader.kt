package com.vini.designsystem.compose.loader

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vini.designsystem.compose.dialog.NonDismissibleDialog
import com.vini.designsystem.compose.theme.ViniBankTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class LoaderState(
    val visible: Boolean = false
)

interface LoaderComponent {
    val loaderState: StateFlow<LoaderState>
    fun showLoader()
    fun hideLoader()
}

class LoaderComponentViewModel : LoaderComponent {
    private val _loaderState = MutableStateFlow(LoaderState())
    override val loaderState = _loaderState.asStateFlow()

    override fun showLoader() {
        _loaderState.update { it.copy(visible = true) }
    }

    override fun hideLoader() {
        _loaderState.update { it.copy(visible = false) }
    }
}

@Composable
fun Loader(state: StateFlow<LoaderState>, content: @Composable () -> Unit = {}) = if (state.collectAsStateWithLifecycle().value.visible) {
    NonDismissibleDialog { LoaderContent() }
} else {
    content()
}

@Composable
private fun LoaderContent() = Box(
    modifier = Modifier
        .size(76.dp)
        .background(
            color = Color.LightGray,
            shape = RoundedCornerShape(4.dp)
        )
) {
    CircularProgressIndicator(
        modifier = Modifier.align(Alignment.Center),
        color = Color.Blue
    )
}

fun loaderStateMock(isVisible: Boolean = true) = MutableStateFlow(LoaderState(visible = isVisible))

@Preview(showBackground = true)
@Composable
private fun LoaderPreview() {
    ViniBankTheme {
        Loader(state = loaderStateMock()) {}
    }
}