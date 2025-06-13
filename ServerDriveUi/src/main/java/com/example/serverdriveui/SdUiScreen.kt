package com.example.serverdriveui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.ScreenModel
import com.example.serverdriveui.ui.component.manager.Component
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
fun SdUiScreen2(
    model: ScreenModel,
    navHostController: NavHostController,
    viewModel: SdUiViewModel2 = koinViewModel { parametersOf(model) },
) {

    val scope = rememberCoroutineScope()
    val components = viewModel.components.collectAsState().value

    SdUiUI(
        components,
        //viewModel.loaderState,
        navHostController
    )


    LaunchedEffect(true) {
        scope.launch {
            viewModel.initialize()?.collect {
                println("iniciou a tela")
            }
        }
        scope.launch {
            viewModel.navigateOnSuccess.collect {
                println("pegou as infos da tela")
                it?.run { navHostController.navigate(SdUiDestination2(this)) }
            }
        }
    }
}

@Composable
fun a() {
    Text("salve")
}

@Composable
fun b() {
    Column {
        Text("vish")
    }
}

@Composable
fun c() {
    Column {
        Text("vish2")
    }
}

@Composable
private fun SdUiUI(
    components: List<Component>,
    //loaderState: StateFlow<LoaderState>,
    navHostController: NavHostController
) {
    Column(Modifier.fillMaxSize()) {
        //Loader(loaderState)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
                .weight(1f)
        ) {
            println("starting lazy")
            itemsIndexed(components) { _, component ->
                println(component::class)
                //component.getComponent(navHostController).invoke(this@LazyColumn)
            }
        }


    }


}