package com.example.serverdriveui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.util.asValue
import com.vini.designsystem.compose.loader.Loader
import com.vini.designsystem.compose.loader.LoaderState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.JsonObject
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

data class SdUiModel(
    val flow: String,
    val toScreen: String = "",
    val screenData: JsonObject,
    val fromScreen: String = "",
)

@Composable
fun SdUiScreen(
    jsonModel: JsonObject,
    navHostController: NavHostController,
    viewModel: SdUiViewModel = koinViewModel { parametersOf(jsonModel) },
) {

    val scope = rememberCoroutineScope()

    SdUiUI(
        viewModel.components.asValue(),
        viewModel.loaderState,
        navHostController
    )

    LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
        viewModel.initialize().launchIn(scope)
        viewModel.navigateOnSuccess.map { navHostController.navigate(SdUiScreenRoute(it)) }
            .launchIn(scope)
    }

    LifecycleEventEffect(Lifecycle.Event.ON_STOP) {
        viewModel.doOnStop()
    }
}

@Composable
fun SdUiUI(
    components: List<Component>,
    loaderState: StateFlow<LoaderState>,
    navHostController: NavHostController
) {
    Loader(loaderState)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        components.forEach { it.getComponentAsColumn(navHostController).invoke(this) }
    }
}