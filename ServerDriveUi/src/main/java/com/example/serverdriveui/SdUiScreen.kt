package com.example.serverdriveui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.component.manager.Component
import com.vini.designsystem.compose.loader.Loader
import com.vini.designsystem.compose.loader.LoaderState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

data class SdUiModel(
    val flowId: String,
    val screenId: String = "",
    val screenData: String,
    val lastScreenId: String = "",
)

@Composable
fun SdUiScreen(
    jsonModel: String,
    navHostController: NavHostController,
    viewModel: SdUiViewModel = koinViewModel { parametersOf(jsonModel) },
) {

    val scope = rememberCoroutineScope()

    SdUiUI(
        viewModel.components.collectAsState().value,
        viewModel.loaderState,
        navHostController
    )

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {

    }

    LaunchedEffect(true) {
        scope.launch {
            viewModel.initialize()?.collect {}
        }
        scope.launch {
            viewModel.navigateOnSuccess.collect {
                navHostController.navigate(SdUiRoute(it))
            }
        }
    }
}

@Composable
private fun SdUiUI(
    components: List<Component>,
    loaderState: StateFlow<LoaderState>,
    navHostController: NavHostController
) {
    Loader(loaderState)
    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        components.forEach { it.getComponent(navHostController).invoke(this@Column) }
    }
}