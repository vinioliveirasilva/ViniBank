package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.properties.HorizontalAlignmentComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalAlignmentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingProperty
import com.example.serverdriveui.ui.component.properties.VerticalArrangementComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalArrangementProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingProperty
import com.example.serverdriveui.ui.component.properties.WeightComponentModifier
import com.example.serverdriveui.ui.component.properties.WeightModifier
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
) : BaseComponent(model, validatorParser, stateManager),
    VerticalFillTypeComponentProperty by VerticalFillTypeProperty(properties, stateManager),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(
        properties,
        stateManager
    ),
    VerticalPaddingComponentProperty by VerticalPaddingProperty(properties, stateManager),
    HorizontalPaddingComponentProperty by HorizontalPaddingProperty(
        properties,
        stateManager
    ),
    HorizontalAlignmentComponentProperty by HorizontalAlignmentProperty(
        properties,
        stateManager
    ),
    VerticalArrangementComponentProperty by VerticalArrangementProperty(
        properties,
        stateManager
    ),
    WeightComponentModifier by WeightModifier(properties, stateManager) {

    @Composable
    override fun getComponent(navController: NavHostController): @Composable ColumnScope.() -> Unit =
        {
            Column(
                verticalArrangement = getVerticalArrangement().asValue(),
                horizontalAlignment = getHorizontalAlignment().asValue(),
                modifier = Modifier
                    .then(verticalFillTypeModifier)
                    .then(horizontalFillTypeModifier)
                    .then(horizontalPaddingModifier)
                    .then(verticalPaddingModifier)
                    .then(weightModifier)
            ) {
                componentParser.parse(data = model, componentStateManager = stateManager).forEach {
                    it.getComponent(navController).invoke(this)
                }
            }
        }

    companion object {
        const val IDENTIFIER = "column"
    }
}