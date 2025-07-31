package com.example.serverdriveui.ui.component.components.sdui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.components.sdui.properties.FlowIdentifierComponent
import com.example.serverdriveui.ui.component.components.sdui.properties.FlowIdentifierProperty
import com.example.serverdriveui.ui.component.components.sdui.properties.FromScreenIdentifierComponent
import com.example.serverdriveui.ui.component.components.sdui.properties.FromScreenIdentifierComponentProperty
import com.example.serverdriveui.ui.component.components.sdui.properties.ScreenDataComponent
import com.example.serverdriveui.ui.component.components.sdui.properties.ScreenDataProperty
import com.example.serverdriveui.ui.component.components.sdui.properties.StageIdentifierComponent
import com.example.serverdriveui.ui.component.components.sdui.properties.StageIdentifierProperty
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.JsonUtil.asBoolean
import com.example.serverdriveui.util.asValue
import com.vini.designsystem.compose.loader.Loader2
import kotlinx.serialization.json.JsonObject

interface RequestUpdateActor {
    @Composable
    fun updateHasBeenRequested(): Boolean
    fun setUpdateHasBeenRequested(hasRequested: Boolean = true)
}

class RequestUpdateActorProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : RequestUpdateActor, BasePropertyData<Boolean>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "requestUpdate",
    transformToData = { it?.asBoolean() },
    defaultPropertyValue = false,
) {
    @Composable
    override fun updateHasBeenRequested() = getValue()
    override fun setUpdateHasBeenRequested(hasRequested: Boolean) = setValue(hasRequested)
}

class SdUiComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val viewModel: SdUiComponentViewModel,
    private val actionParser: ActionParser,
    private val componentParser: ComponentParser,
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser),
    FlowIdentifierComponent by FlowIdentifierProperty(properties, stateManager),
    ScreenDataComponent by ScreenDataProperty(properties, stateManager),
    StageIdentifierComponent by StageIdentifierProperty(properties, stateManager),
    FromScreenIdentifierComponent by FromScreenIdentifierComponentProperty(
        properties,
        stateManager
    ),
    RequestUpdateActor by RequestUpdateActorProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable (() -> Unit) =
        {
            val updateHasBeenRequested = updateHasBeenRequested()
            val componentsFromVm = viewModel.components.asValue()
            val componentsFromModel = componentParser.parseList(model)

            LaunchedEffect(updateHasBeenRequested) {
                if (updateHasBeenRequested) {
                    viewModel.initialize(
                        flowId = getFlowIdentifier(),
                        screenId = getStageIdentifier(),
                        screenData = getScreenData(),
                        fromScreen = getFromScreenIdentifier()
                    )
                }
                setUpdateHasBeenRequested(false)
            }

            Column(
                modifier = modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                Loader2(viewModel.loaderState) {
                    when {
                        componentsFromVm.isEmpty() && componentsFromModel.isEmpty() -> setUpdateHasBeenRequested()
                        componentsFromVm.isEmpty() -> componentsFromModel.forEach { it.getComponentAsColumn(navController).invoke(this) }
                        else -> componentsFromVm.forEach { it.getComponentAsColumn(navController).invoke(this) }
                    }
                }
            }
        }

    companion object {
        const val IDENTIFIER = "sdui"
    }
}