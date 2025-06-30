package com.example.serverdriveui.ui.component.components.sdui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
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
import com.example.serverdriveui.util.asValue
import com.google.gson.JsonObject
import com.vini.designsystem.compose.loader.Loader2

class SdUiComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val viewModel: SdUiComponentViewModel,
) : BaseComponent(model, properties, stateManager, validatorParser),
    FlowIdentifierComponent by FlowIdentifierProperty(properties, stateManager),
    StageIdentifierComponent by StageIdentifierProperty(properties, stateManager) {

    init {
        viewModel.initialize(
            flowId = getFlowIdentifier(),
            screenId = getStageIdentifier(),
            screenData = "{}"
        )
    }

    @Composable
    override fun getInternalComponent(navController: NavHostController, modifier: Modifier): @Composable (() -> Unit) =
        {
            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Loader2(viewModel.loaderState) {
                    viewModel.components.asValue().forEach {
                        it.getComponentAsColumn(navController).invoke(this)
                    }
                }
            }
        }

    companion object {
        const val IDENTIFIER = "sdui"
    }
}