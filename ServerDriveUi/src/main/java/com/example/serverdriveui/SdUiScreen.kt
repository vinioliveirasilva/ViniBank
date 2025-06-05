package com.example.serverdriveui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.components.manager.Component
import com.vini.designsystem.compose.loader.Loader
import com.vini.designsystem.compose.loader.LoaderState
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SdUiScreen(
    screenId: String,
    screenData: String,
    navHostController: NavHostController,
    viewModel: SdUiViewModel = koinViewModel { parametersOf(screenId, screenData) },
) {
    SdUiUI(
        viewModel.components.collectAsState().value,
        viewModel.loaderState,
        navHostController
    )
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