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
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.ScreenModel
import com.example.serverdriveui.ui.component.manager.Component
import com.vini.designsystem.compose.loader.Loader
import com.vini.designsystem.compose.loader.LoaderState
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

data class SdUiModel(
    val flowId: String,
    val screenId: String,
    val screenData: String,
    val lastScreenId: String,
)

@Composable
fun SdUiScreen(
    model: SdUiModel,
    navHostController: NavHostController,
    viewModel: SdUiViewModel = koinViewModel { parametersOf(model) },
) {

    val scope = rememberCoroutineScope()

    SdUiUI(
        viewModel.components.collectAsState().value,
        viewModel.loaderState,
        navHostController
    )
}

@Composable
fun SdUiScreen2(
    model: ScreenModel,
    navHostController: NavHostController,
    viewModel: SdUiViewModel2 = koinViewModel { parametersOf(model) },
) {

    val scope = rememberCoroutineScope()

    SdUiUI(
        viewModel.components.collectAsState().value,
        viewModel.loaderState,
        navHostController
    )


    LaunchedEffect(viewModel.navigateOnSuccess) {
        scope.launch {
            viewModel.navigateOnSuccess.collect {
                it?.run { navHostController.navigate(SdUiDestination2(this)) }
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        components.forEach { it.getComponent(navHostController).invoke(this) }
    }
}