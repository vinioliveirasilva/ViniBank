package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.actions.manager.Action
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.dynamic.EnabledComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.EnabledProperty
import com.example.serverdriveui.ui.component.properties.dynamic.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.TextProperty
import com.example.serverdriveui.ui.component.properties.static.FillTypeComponentModifier
import com.example.serverdriveui.ui.component.properties.static.FillTypeModifier
import com.example.serverdriveui.ui.component.properties.static.PaddingComponentModifier
import com.example.serverdriveui.ui.component.properties.static.PaddingModifier
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator

/**
 * A component that renders an [OutlinedButton]. This behaves the same as [ButtonComponent]
 * but uses an outlined style button from Material3.
 */
data class OutlinedButtonComponent(
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
            OutlinedButton(
                enabled = isEnabled,
                modifier = Modifier
                    .then(fillTypeModifier)
                    .then(paddingModifier),
                onClick = { action.execute(navController) },
                content = { Text(getText().collectAsState().value) }
            )
        }

    companion object {
        const val IDENTIFIER = "outlinedButton"
    }
}
