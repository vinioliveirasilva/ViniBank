package com.example.serverdriveui.ui.component.components.bottomnavigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.components.bottomnavigation.properties.BottomNavigationDestinationComponent
import com.example.serverdriveui.ui.component.components.bottomnavigation.properties.BottomNavigationDestinationIndexComponent
import com.example.serverdriveui.ui.component.components.bottomnavigation.properties.BottomNavigationDestinationIndexProperty
import com.example.serverdriveui.ui.component.components.bottomnavigation.properties.BottomNavigationDestinationProperty
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
) : BaseComponent(model, validatorParser, stateManager),
    BottomNavigationDestinationComponent by BottomNavigationDestinationProperty(
        properties,
        stateManager
    ),
    BottomNavigationDestinationIndexComponent by BottomNavigationDestinationIndexProperty(
        properties,
        stateManager
    ) {
    @Composable
    override fun getComponent(navController: NavHostController): @Composable (ColumnScope.() -> Unit) =
        {
            val selectedDestination = getSelectedDestination().collectAsState().value
            val index = getIndex().collectAsState().value
            val selected = selectedDestination == index
            val componentTag = if (selected) "selectedIcon" else "unselectedIcon"

            Row(Modifier.weight(1f)) {
                NavigationBarItem(
                    modifier = Modifier.wrapContentSize(),
                    selected = selected,
                    onClick = { setSelectedDestination(index) },
                    icon = {
                        componentParser.parse(
                            model,
                            componentTag,
                            componentStateManager = stateManager
                        ).forEach {
                            Column {
                                it.getComponent(navController).invoke(this)
                            }
                        }
                    },
                    label = {
                        componentParser.parse(model, componentStateManager = stateManager)
                            .forEach {
                                Column(Modifier.wrapContentSize()) {
                                    it.getComponent(navController).invoke(this)
                                }
                            }
                    }
                )
            }
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