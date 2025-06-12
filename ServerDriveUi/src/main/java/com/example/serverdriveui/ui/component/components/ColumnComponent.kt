package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalAlignmentComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalAlignmentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalPaddingProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalArrangementComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalArrangementProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalPaddingProperty
import com.example.serverdriveui.ui.component.properties.dynamic.WeightComponentModifier
import com.example.serverdriveui.ui.component.properties.dynamic.WeightModifier
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator
import com.example.serverdriveui.util.asValue

class ColumnComponent(
    private val dynamicProperties: List<PropertyModel>,
    private val components: List<Component>,
    private val validators: List<Validator>,
    private val stateManager: ComponentStateManager,
) : Component,
    VerticalFillTypeComponentProperty by VerticalFillTypeProperty(dynamicProperties, stateManager),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(dynamicProperties, stateManager),
    VerticalPaddingComponentProperty by VerticalPaddingProperty(dynamicProperties, stateManager),
    HorizontalPaddingComponentProperty by HorizontalPaddingProperty(dynamicProperties, stateManager),
    HorizontalAlignmentComponentProperty by HorizontalAlignmentProperty(dynamicProperties, stateManager),
    VerticalArrangementComponentProperty by VerticalArrangementProperty(dynamicProperties, stateManager),
    WeightComponentModifier by WeightModifier(dynamicProperties, stateManager) {

    init {
        validators.forEach { it.initialize() }
    }

    @Composable
    override fun getComponent(navController: NavHostController): @Composable ColumnScope.() -> Unit =
        {
            Column(
                verticalArrangement = getVerticalArrangement().asValue(),
                horizontalAlignment = getHorizontalAlignment().asValue(),
                modifier = Modifier
                    .then(Modifier.fillMaxWidth())
                    .then(Modifier.fillMaxHeight())
                    .then(horizontalPaddingModifier)
                    .then(verticalPaddingModifier)
                    .then(weightModifier)
            ) {
                components.forEach { it.getComponent(navController).invoke(this) }
            }
        }

    companion object {
        const val IDENTIFIER = "column"
    }
}

@Preview
@Composable
fun ColumnComponentPreview() {
    val navHostController = NavHostController(LocalContext.current)
    Column(modifier = Modifier.fillMaxSize()) {
        ColumnComponent(
            dynamicProperties = emptyList(),
            components = listOf(
                TextComponent(
                    listOf(PropertyModel(name = "text", value = "Item")),
                    emptyList(),
                    object : Action { override fun execute(navController: NavHostController) {} },
                    ComponentStateManager(),
                )
            ),
            validators = emptyList(),
            stateManager = ComponentStateManager(),
        ).getComponent(navController = navHostController).invoke(this)
    }
}