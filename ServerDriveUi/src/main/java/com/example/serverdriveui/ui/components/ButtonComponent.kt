package com.example.serverdriveui.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.actions.manager.Action
import com.example.serverdriveui.ui.components.manager.Component
import com.example.serverdriveui.ui.components.properties.dynamic.EnabledComponentProperty
import com.example.serverdriveui.ui.components.properties.dynamic.EnabledProperty
import com.example.serverdriveui.ui.components.properties.dynamic.TextComponentProperty
import com.example.serverdriveui.ui.components.properties.dynamic.TextProperty
import com.example.serverdriveui.ui.components.properties.static.FillTypeComponentModifier
import com.example.serverdriveui.ui.components.properties.static.FillTypeModifier
import com.example.serverdriveui.ui.components.properties.static.PaddingComponentModifier
import com.example.serverdriveui.ui.components.properties.static.PaddingModifier
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.Validator

data class ButtonComponent(
    private val dynamicProperties: List<PropertyModel>,
    private val staticProperties: Map<String, String>,
    private val action: Action,
    private val stateManager: ComponentStateManager,
    private val validators: List<Validator>,
) : Component,
    TextComponentProperty by TextProperty(dynamicProperties, stateManager),
    FillTypeComponentModifier by FillTypeModifier(staticProperties),
    PaddingComponentModifier by PaddingModifier(staticProperties),
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
                    .then(fillTypeModifier)
                    .then(paddingModifier),
                onClick = { action.execute(navController) },
                content = { Text(getText().collectAsState().value) }
            )
        }

    companion object {
        const val IDENTIFIER = "button"
    }
}