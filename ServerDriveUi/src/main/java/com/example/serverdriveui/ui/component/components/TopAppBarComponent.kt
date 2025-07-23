package com.example.serverdriveui.ui.component.components

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.JsonObject

data class TopAppBarComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
    private val actionParser: ActionParser,
    private val scope: CoroutineScope
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser, scope) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        CenterAlignedTopAppBar(
            modifier = Modifier
                .then(horizontalFillTypeModifier),
            title = {
                componentParser.parseList(
                    data = model
                ).forEach {
                    it.getComponent(navController).invoke()
                }
            },
            navigationIcon = {
                componentParser.parseList(
                    data = model,
                    componentTag = "navigationIcons"
                ).forEach {
                    it.getComponent(navController).invoke()
                }
            },
            actions = {
                componentParser.parseList(
                    data = model,
                    componentTag = "actionIcons"
                ).forEach {
                    it.getComponentAsRow(navController).invoke(this)
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

    //SdUiComponentPreview(jsonModel)
}
