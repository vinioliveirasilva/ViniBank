package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.properties.HorizontalAlignmentComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalAlignmentProperty
import com.example.serverdriveui.ui.component.properties.VerticalArrangementComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalArrangementProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.asValue
import com.google.gson.JsonObject

class ColumnComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser),
    HorizontalAlignmentComponentProperty by HorizontalAlignmentProperty(
        properties,
        stateManager
    ),
    VerticalArrangementComponentProperty by VerticalArrangementProperty(
        properties,
        stateManager
    ) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier
    ): @Composable () -> Unit = {
            val action = actionParser.parse(model, componentStateManager = stateManager)
            val actionModifier = Modifier.clickable(action != null) { action?.execute(navController) }

            Column(
                verticalArrangement = getVerticalArrangement().asValue(),
                horizontalAlignment = getHorizontalAlignment().asValue(),
                modifier = modifier
                    .then(actionModifier)
            ) {
                componentParser.parse(data = model, componentStateManager = stateManager).forEach {
                    it.getComponentAsColumn(navController).invoke(this)
                }
            }
        }

    companion object {
        const val IDENTIFIER = "column"
    }
}