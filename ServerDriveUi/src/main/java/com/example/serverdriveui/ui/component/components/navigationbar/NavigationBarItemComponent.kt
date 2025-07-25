package com.example.serverdriveui.ui.component.components.navigationbar

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.components.navigationbar.properties.NavigationDestinationComponent
import com.example.serverdriveui.ui.component.components.navigationbar.properties.NavigationDestinationIndexComponent
import com.example.serverdriveui.ui.component.components.navigationbar.properties.NavigationDestinationIndexProperty
import com.example.serverdriveui.ui.component.components.navigationbar.properties.NavigationDestinationProperty
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.manager.SdUiComponentPreview
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject

class NavigationBarItemComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
) : BaseComponent(model, properties, stateManager, validatorParser),
    NavigationDestinationComponent by NavigationDestinationProperty(
        properties,
        stateManager
    ),
    NavigationDestinationIndexComponent by NavigationDestinationIndexProperty(
        properties,
        stateManager
    ) {

    @Composable
    override fun getComponentAsRow(navController: NavHostController): @Composable (RowScope.() -> Unit) = {
        val selectedDestination = getSelectedDestination().collectAsState().value
        val index = getIndex().collectAsState().value
        val selected = selectedDestination == index
        val componentTag = if (selected) "selectedIcon" else "unselectedIcon"

        NavigationBarItem(
            selected = selected,
            onClick = { setSelectedDestination(index) },
            icon = {
                componentParser.parseList(
                    model,
                    componentTag,
                    componentStateManager = stateManager
                ).forEach { it.getComponent(navController).invoke() }
            },
            label = {
                componentParser.parseList(model, componentStateManager = stateManager)
                    .forEach { it.getComponent(navController).invoke() }
            }
        )
    }

    companion object {
        const val IDENTIFIER = "navigationBarItem"
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationBarItemComponentPreview() {
    SdUiComponentPreview(
        componentModel = """
            "type": "navigationBarItem",
            "properties": [
                {
                    "name": "index",
                    "value": "0"
                }
            ],
            "components": [
                {
                    "type": "text",
                    "properties": [
                        {
                            "name": "text",
                            "value": "Home"
                        }
                    ]
                }
            ],
            "selectedIcon": [
                {
                    "type": "icon",
                    "properties": [
                        {
                            "name": "icon",
                            "value": "Home"
                        }
                    ]
                }
            ],
            "unselectedIcon": [
                {
                    "type": "icon",
                    "properties": [
                        {
                            "name": "icon",
                            "value": "Payment"
                        }
                    ]
                }
            ]
       
        """.trimIndent()
    )
}