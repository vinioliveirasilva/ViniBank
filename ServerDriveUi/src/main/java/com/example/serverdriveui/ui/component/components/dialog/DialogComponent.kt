package com.example.serverdriveui.ui.component.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.manager.SdUiComponentPreview
import com.example.serverdriveui.ui.component.properties.VisibilityComponentProperty
import com.example.serverdriveui.ui.component.properties.VisibilityProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.asValue
import com.google.gson.JsonObject

class DialogComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
) : BaseComponent(model, properties, stateManager, validatorParser),
    VisibilityComponentProperty by VisibilityProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {

        if (getIsVisible().asValue()) {
            AlertDialog(
                icon = {
                    componentParser.parseList(
                        model,
                        componentStateManager = stateManager,
                        componentTag = "icon"
                    ).forEach { it.getComponent(navController).invoke() }
                },
                title = {
                    componentParser.parseList(
                        model,
                        componentStateManager = stateManager,
                        componentTag = "title"
                    ).forEach { it.getComponent(navController).invoke() }
                },
                text = {
                    componentParser.parseList(
                        model,
                        componentStateManager = stateManager,
                        componentTag = "text"
                    ).forEach { it.getComponent(navController).invoke() }
                },
                onDismissRequest = { setIsVisible(false) },
                confirmButton = {
                    TextButton(
                        onClick = {

                        }
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { setIsVisible(false) },
                    ) {
                        Text("Dismiss")
                    }
                }
            )
        }
    }

    companion object {
        const val IDENTIFIER = "dialog"
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    val jsonModel = """
        "type": "dialog",
        "properties": [
            { "name": "shouldShow", "value" : "true" }
        ],
        "icon" : [
            {
                "type": "icon",
                "properties": [
                    { "name": "icon", "value": "Warning" }
                ]
            }
        ],
        "title" : [
            {
                "type": "text",
                "properties": [
                    { "name": "text", "value" :"Dialog title, can be anything, just for example" }
                ]
            }
        ],
        "text" : [
            {
                "type": "text",
                "properties": [
                    { "name": "text", "value" :"Dialog text, can be anything, just for example" }
                ]
            }
        ]
    """

    SdUiComponentPreview(jsonModel)
}
