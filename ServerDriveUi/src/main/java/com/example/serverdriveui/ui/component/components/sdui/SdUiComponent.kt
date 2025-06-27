package com.example.serverdriveui.ui.component.components.sdui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.components.sdui.properties.FlowIdentifierComponent
import com.example.serverdriveui.ui.component.components.sdui.properties.FlowIdentifierProperty
import com.example.serverdriveui.ui.component.components.sdui.properties.StageIdentifierComponent
import com.example.serverdriveui.ui.component.components.sdui.properties.StageIdentifierProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject
import com.vini.designsystem.compose.loader.Loader2

class SdUiComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val viewModel: SdUiComponentViewModel,
) : BaseComponent(model, validatorParser, stateManager),
    FlowIdentifierComponent by FlowIdentifierProperty(properties, stateManager),
    StageIdentifierComponent by StageIdentifierProperty(properties, stateManager) {

    @Composable
    override fun getComponent(navController: NavHostController): @Composable (ColumnScope.() -> Unit) =
        {
            val flowId = getFlowIdentifier().collectAsState().value
            val stageId = getStageIdentifier().collectAsState().value
            val components = viewModel.components.collectAsState().value

            LaunchedEffect(stageId) {
                viewModel.initialize(
                    flowId = flowId,
                    screenId = stageId,
                    screenData = "{}"//screenData = getScreenData().collectAsState().value
                )
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Loader2(viewModel.loaderState) {
                    components.forEach {
                        it.getComponent(navController).invoke(this)
                    }
                }
            }
        }

    companion object {
        const val IDENTIFIER = "sdui"
    }
}