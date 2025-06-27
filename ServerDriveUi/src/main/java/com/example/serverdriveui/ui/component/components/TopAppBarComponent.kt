package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.manager.SdUiComponentPreview
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject

data class TopAppBarComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
) : BaseComponent(model, validatorParser, stateManager),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(properties, stateManager) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun getComponent(navController: NavHostController): @Composable ColumnScope.() -> Unit =
        {
            CenterAlignedTopAppBar(
                modifier = Modifier
                    .then(horizontalFillTypeModifier),
                title = {
                    componentParser.parse(
                        data = model,
                        componentStateManager = stateManager
                    ).forEach {
                        it.getComponent(navController).invoke(this)
                    }
                },
                navigationIcon = {
                    componentParser.parse(
                        data = model,
                        componentTag = "navigationIcons",
                        componentStateManager = stateManager
                    ).forEach {
                        it.getComponent(navController).invoke(this)
                    }
                },
                actions = {
                    componentParser.parse(
                        data = model,
                        componentTag = "actionIcons",
                        componentStateManager = stateManager
                    ).forEach {
                        Column {
                            it.getComponent(navController).invoke(this)
                        }
                    }
                }
            )
        }

    companion object {
        const val IDENTIFIER = "topAppBar"
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppBarComponentPreview() {
    val jsonModel = """
            "type": "topAppBar",
            "properties": [
            ],
            "components": [
                {
                    "type": "text",
                    "properties": [
                        {
                            "name": "text",
                            "value": "Salve"
                        }
                    ]
                }
            ],
            "navigationIcons": [
                {
                    "type": "button",
                    "properties": [
                        {
                            "name": "text",
                            "value": "Salve"
                        }
                    ]
                }
            ],
            "actionIcons": [
                {
                    "type": "button",
                    "properties": [
                        {
                            "name": "text",
                            "value": "Salve"
                        }
                    ]
                }
            ]
    """

    SdUiComponentPreview(jsonModel)
}
