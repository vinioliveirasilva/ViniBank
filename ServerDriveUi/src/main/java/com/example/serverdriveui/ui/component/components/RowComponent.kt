package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalArrangementComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalArrangementProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalPaddingProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalAlignmentComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalAlignmentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalPaddingProperty
import com.example.serverdriveui.ui.component.properties.dynamic.WeightComponentModifier
import com.example.serverdriveui.ui.component.properties.dynamic.WeightModifier
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator
import com.example.serverdriveui.util.asValue

class RowComponent(
    private val dynamicProperties: List<PropertyModel>,
    private val components: List<Component>,
    private val validators: List<Validator>,
    private val stateManager: ComponentStateManager,
) : Component,
    VerticalFillTypeComponentProperty by VerticalFillTypeProperty(dynamicProperties, stateManager),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(dynamicProperties, stateManager),
    VerticalPaddingComponentProperty by VerticalPaddingProperty(dynamicProperties, stateManager),
    HorizontalPaddingComponentProperty by HorizontalPaddingProperty(dynamicProperties, stateManager),
    VerticalAlignmentComponentProperty by VerticalAlignmentProperty(dynamicProperties, stateManager),
    HorizontalArrangementComponentProperty by HorizontalArrangementProperty(dynamicProperties, stateManager),
    WeightComponentModifier by WeightModifier(dynamicProperties, stateManager) {

    init {
        validators.forEach { it.initialize() }
    }

    @Composable
    override fun getComponent(navController: NavHostController): @Composable LazyListScope.() -> Unit = {

        val scope = this
        Row(
            verticalAlignment = getVerticalAlignment().asValue(),
            horizontalArrangement = getHorizontalArrangement().asValue(),
            modifier = Modifier
                .then(horizontalFillTypeModifier)
                .then(verticalFillTypeModifier)
                .then(horizontalPaddingModifier)
                .then(verticalPaddingModifier)
                //.then(weightModifier)
        ) {
            itemsIndexed(components) {_, component ->
                component.getComponent(navController).invoke(scope)
            }
        }
    }

    companion object {
        const val IDENTIFIER = "row"
    }
}