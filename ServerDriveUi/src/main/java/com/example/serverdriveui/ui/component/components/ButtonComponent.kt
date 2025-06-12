package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.platform.LocalContext
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.actions.manager.Action
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.dynamic.EnabledComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.EnabledProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalPaddingProperty
import com.example.serverdriveui.ui.component.properties.dynamic.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.TextProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalPaddingProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator

data class ButtonComponent(
    private val dynamicProperties: List<PropertyModel>,
    private val validators: List<Validator>,
    private val action: Action,
    private val stateManager: ComponentStateManager,
) : Component,
    TextComponentProperty by TextProperty(dynamicProperties, stateManager),
    VerticalFillTypeComponentProperty by VerticalFillTypeProperty(dynamicProperties, stateManager),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(dynamicProperties, stateManager),
    VerticalPaddingComponentProperty by VerticalPaddingProperty(dynamicProperties, stateManager),
    HorizontalPaddingComponentProperty by HorizontalPaddingProperty(dynamicProperties, stateManager),
    EnabledComponentProperty by EnabledProperty(dynamicProperties, stateManager) {

    init {
        validators.forEach { it.initialize() }
    }

    @Composable
    override fun getComponent(navController: NavHostController): @Composable ColumnScope.() -> Unit =
        {
            val isEnabled = getEnabled().collectAsState().value
            Button(
                enabled = isEnabled,
                modifier = Modifier
                    .then(horizontalFillTypeModifier)
                    .then(verticalFillTypeModifier)
                    .then(horizontalPaddingModifier)
                    .then(verticalPaddingModifier),
                onClick = { action.execute(navController) },
                content = { Text(getText().collectAsState().value) }
            )
        }

    companion object {
        const val IDENTIFIER = "button"
    }
}

@Preview
@Composable
fun ButtonComponentPreview() {
    val navHostController = NavHostController(LocalContext.current)
    Column(modifier = Modifier.fillMaxSize()) {
        ButtonComponent(
            listOf(PropertyModel(name = "text", value = "Button")),
            emptyList(),
            object : Action {
                override fun execute(navController: NavHostController) {}
            },
            ComponentStateManager(),
        ).getComponent(navController = navHostController).invoke(this)
    }
}