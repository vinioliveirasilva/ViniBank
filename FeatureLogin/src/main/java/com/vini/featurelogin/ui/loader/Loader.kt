package com.vini.featurelogin.ui.loader

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vini.featurelogin.ui.dialog.NonDismissibleDialog
import com.vini.featurelogin.ui.theme.ViniBankTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

sealed class LoaderState {
    data object Showed : LoaderState()
    data object Hidden : LoaderState()
}

interface LoaderComponent {
    val loaderEvent: State<LoaderState>
    fun setupLoader(scope: CoroutineScope)
    fun showLoader()
    fun hideLoader()
}

class LoaderComponentViewModel : LoaderComponent {
    private var internalScope: CoroutineScope? = null
    override val loaderEvent = mutableStateOf<LoaderState>(LoaderState.Hidden)

    override fun setupLoader(scope: CoroutineScope) {
        internalScope = scope
    }

    override fun showLoader() {
        loaderEvent.value = LoaderState.Showed
    }

    override fun hideLoader() {
        loaderEvent.value = LoaderState.Hidden
    }
}

@Composable
fun Loader(state: State<LoaderState>) = with(
    state
) {
    when (value) {
        is LoaderState.Showed -> NonDismissibleDialog { LoaderContent() }
        is LoaderState.Hidden -> {}
    }
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
        modifier = Modifier
            .align(Alignment.Center),
        color = Color.Blue
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview(showBackground = true)
@Composable
private fun LoaderPreview() {
    ViniBankTheme {
        Loader(state = derivedStateOf { LoaderState.Showed })
    }
}