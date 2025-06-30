package com.example.serverdriveui.ui.component.components

import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.properties.EnabledComponentProperty
import com.example.serverdriveui.ui.component.properties.EnabledProperty
import com.example.serverdriveui.ui.component.properties.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.TextProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject

/**
 * A component that renders an [ElevatedButton]. This behaves the same as [ButtonComponent]
 * but uses an elevated style button from Material3.
 */
data class ElevatedButtonComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser),
    TextComponentProperty by TextProperty(properties, stateManager),
    EnabledComponentProperty by EnabledProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
            val isEnabled = getEnabled().collectAsState().value
            ElevatedButton(
                enabled = isEnabled,
                modifier = modifier,
                onClick = {
                    actionParser.parse(
                        componentJsonModel = model,
                        componentStateManager = stateManager
                    )?.execute(navController)
                },
                content = { Text(getText().collectAsState().value) }
            )
        }

    companion object {
        const val IDENTIFIER = "elevatedButton"
    }
}

