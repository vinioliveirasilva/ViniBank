package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.manager.SdUiComponentPreview
import com.example.serverdriveui.ui.component.properties.EnabledComponentProperty
import com.example.serverdriveui.ui.component.properties.EnabledProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingProperty
import com.example.serverdriveui.ui.component.properties.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.TextProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject

data class ButtonComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, validatorParser, stateManager),
    TextComponentProperty by TextProperty(properties, stateManager),
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
    EnabledComponentProperty by EnabledProperty(properties, stateManager) {

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
                onClick = {
                    actionParser.parse(
                        componentJsonModel = model,
                        componentStateManager = stateManager
                    ).execute(navController)
                },
                content = { Text(getText().collectAsState().value) },
            )
        }

    companion object {
        const val IDENTIFIER = "button"
    }
}


@Preview(showBackground = true)
@Composable
fun ButtonComponentPreview() {
    val jsonModel = """
        "type": "button",
        "properties": [
            {
                "name": "text",
                "value": "Salve"
            }
    """

    SdUiComponentPreview(jsonModel)
}