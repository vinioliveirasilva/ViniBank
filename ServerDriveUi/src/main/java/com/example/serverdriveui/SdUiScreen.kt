package com.example.serverdriveui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.serverdriveui.di.ServerDriverUiModules
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.ScreenUtil.component
import com.example.serverdriveui.util.ScreenUtil.property
import com.example.serverdriveui.util.ScreenUtil.screen
import com.example.serverdriveui.util.asValue
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.vini.designsystem.compose.loader.Loader
import com.vini.designsystem.compose.loader.LoaderState
import com.vini.designsystem.compose.theme.ViniBankTheme
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import org.json.JSONObject
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.LocalKoinScope
import org.koin.core.parameter.parametersOf

data class SdUiModel(
    val flow: String,
    val toScreen: String = "",
    val screenData: String,
    val fromScreen: String = "",
)

@Composable
fun SdUiScreen(
    jsonModel: String,
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
        viewModel.initialize()?.launchIn(scope)
        viewModel.navigateOnSuccess.map { navHostController.navigate(SdUiRoute(it)) }
            .launchIn(scope)
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

@Preview(showBackground = true)
@Composable
fun Preview() {
    fun horizontalDivider() = component(
        "horizontalDivider",
        listOf(
            property("paddingVertical", "8")
        )
    )

    fun item(title: String, description: String, icon: String) = component(
        "row",
        listOf(
            property("horizontalFillType", "Max"),
            property("verticalAlignment", "Center"),
            property("paddingVertical", "16"),
        ),
        listOf(
            component(
                "icon",
                listOf(
                    property("icon", icon),
                    property("size", "24"),
                )
            ),
            component(
                "column",
                listOf(
                    property("paddingHorizontal", "16"),
                ),
                listOf(
                    component(
                        "text",
                        listOf(
                            property("text", title),
                        )
                    ),
                    component(
                        "text",
                        listOf(
                            property("text", description),
                        )
                    )
                )
            )
        )
    )

    fun addCard() = component(
        "card",
        listOf(
            property("paddingHorizontal", "10"),
            property("horizontalFillType", "Max"),
            property("height", "180"),
        ),
        listOf(
            component(
                "column",
                listOf(
                    property("paddingHorizontal", "20"),
                    property("paddingVertical", "20"),
                    property("verticalFillType", "Max"),
                    property("horizontalFillType", "Max"),
                    property("horizontalAlignment", "Center"),
                    property("verticalArrangement", "Center"),
                ),
                listOf(
                    component(
                        "icon",
                        listOf(
                            property("icon", "Add"),
                            property("size", "30"),
                        )
                    ),
                ),
            )
        )
    )

    fun getScreenModel(screenData: String) = JSONObject(
        screen(
            "Home",
            "Cartoes",
            "1",
            "",
            false,
            listOf(
                component(
                    "lazyColumn",
                    listOf(
                        property("paddingHorizontal", "16"),
                        property("weight", "1"),
                        property("horizontalFillType", "Max"),
                        property("horizontalAlignment", "Center"),
                    ),
                    listOf(
                        addCard(),
                        item(
                            "Até 3 adicionais",
                            "Conte com até 3 cartoes adicionais gratuitos com os mesmos beneficios do titular.",
                                "Payment"
                        ),
                        horizontalDivider(),
                        item(
                            "ViniBank Shop",
                            "Faça compras no ViniBank Shop com o seu cartão e tenha vantagens como cashback  e parcelamentos sem juros.",
                            "ShoppingBag"
                        ),
                        horizontalDivider(),
                        item(
                            "Concierge",
                            "Conte com assistencia pessoal para te ajudar na organização de viagens, procura de eeventos e incidação dos melhores restaurantes e servicos onde quer que voce esteja.",
                            "SupervisorAccount"
                        ),
                    )
                )
            )
        )
    )

    KoinApplication(application = { modules(ServerDriverUiModules) }) {
        val scope = LocalKoinScope.current
        val componentParser = remember { ComponentParser(scope) }
        val componentStateManager = remember { ComponentStateManager() }
        val navController = rememberNavController()
        val baseComponent = getScreenModel("{}").toString()
        ViniBankTheme {
            Column {
                componentParser.parseList(
                    data = Gson().fromJson(baseComponent, JsonObject::class.java),
                    componentStateManager = componentStateManager
                ).forEach { it.getComponentAsColumn(navController).invoke(this) }
            }
        }
    }
}