package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.properties.HorizontalArrangementComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalArrangementProperty
import com.example.serverdriveui.ui.component.properties.VerticalAlignmentComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalAlignmentProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import kotlinx.serialization.json.JsonObject

class RowComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser),
    VerticalAlignmentComponentProperty by VerticalAlignmentProperty(properties, stateManager),
    HorizontalArrangementComponentProperty by HorizontalArrangementProperty(
        properties,
        stateManager,
    ) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        val actionModifier = actions["OnClick"]?.let { Modifier.clickable { it.execute(navController) } } ?: Modifier

        Row(
            verticalAlignment = getVerticalAlignment(),
            horizontalArrangement = getHorizontalArrangement(),
            modifier = modifier.then(actionModifier)
        ) {
            componentParser.parseList(data = model).forEach {
                it.getComponentAsRow(navController).invoke(this)
            }
        }
    }

    companion object {
        const val IDENTIFIER = "row"
    }
}