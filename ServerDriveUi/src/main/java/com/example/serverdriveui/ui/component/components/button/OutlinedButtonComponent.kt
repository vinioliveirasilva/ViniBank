package com.example.serverdriveui.ui.component.components.button

import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.manager.SdUiComponentPreview
import com.example.serverdriveui.ui.component.properties.EnabledComponentProperty
import com.example.serverdriveui.ui.component.properties.EnabledProperty
import com.example.serverdriveui.ui.component.properties.ShapeComponentProperty
import com.example.serverdriveui.ui.component.properties.ShapeProperty
import com.example.serverdriveui.ui.component.properties.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.TextProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.vini.designsystemsdui.component.elevatedButton
import com.vini.designsystemsdui.component.outlinedButton
import kotlinx.serialization.json.JsonObject

/**
 * A component that renders an [OutlinedButton]. This behaves the same as [ButtonComponent]
 * but uses an outlined style button from Material3.
 */
data class OutlinedButtonComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser),
    TextComponentProperty by TextProperty(properties, stateManager),
    EnabledComponentProperty by EnabledProperty(properties, stateManager),
    ShapeComponentProperty by ShapeProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        val isEnabled = getEnabled()
        OutlinedButton(
            enabled = isEnabled,
            modifier = modifier,
            shape = getShape(),
            onClick = {
                actions["OnClick"]?.execute(navController)
            },
            content = { Text(getText()) }
        )
    }

    companion object {
        const val IDENTIFIER = "outlinedButton"
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    SdUiComponentPreview(
        outlinedButton()
    )
}
