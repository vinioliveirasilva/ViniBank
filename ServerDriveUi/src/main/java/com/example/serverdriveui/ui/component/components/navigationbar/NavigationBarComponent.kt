package com.example.serverdriveui.ui.component.components.navigationbar

import androidx.activity.compose.BackHandler
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.components.navigationbar.properties.NavigationDestinationComponent
import com.example.serverdriveui.ui.component.components.navigationbar.properties.NavigationDestinationProperty
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.manager.SdUiComponentPreview
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject
import org.json.JSONObject

class NavigationBarComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
) : BaseComponent(model, properties, stateManager, validatorParser),
    NavigationDestinationComponent by NavigationDestinationProperty(
        properties,
        stateManager
    ) {
    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier
    ): @Composable (() -> Unit) =
        {
            val destinations = componentParser.parse(model, componentStateManager = stateManager)
            val selectedDestination = getSelectedDestination().collectAsState().value

            BackHandler(enabled = selectedDestination != FIRST_DESTINATION_INDEX) {
                setSelectedDestination(FIRST_DESTINATION_INDEX)
            }

            BottomAppBar(
                content = {
                    destinations.forEach {
                        it.getComponentAsRow(navController).invoke(this)
                    }
                }
            )
        }

    companion object {
        const val IDENTIFIER = "navigationBar"
        private const val FIRST_DESTINATION_INDEX = 0
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationComponentPreview() {
    val bottomNavigation = JSONObject(
        mapOf(
            "type" to "navigationBar",
            "properties" to listOf(
                mapOf(
                    "name" to "selectedDestination",
                    "value" to "0",
                    "id" to "bottomNavigation.selectedDestination"
                ),
            ),
            "components" to listOf(
                mapOf(
                    "type" to "navigationBarItem",
                    "properties" to listOf(
                        mapOf("name" to "index", "value" to "0"),
                        mapOf("name" to "weight", "value" to "1"),
                        mapOf(
                            "name" to "selectedDestination",
                            "value" to "0",
                            "id" to "bottomNavigation.selectedDestination"
                        ),
                    ),
                    "components" to listOf(
                        mapOf(
                            "type" to "text",
                            "properties" to listOf(
                                mapOf("name" to "text", "value" to "Home")
                            )
                        ),
                    ),
                    "selectedIcon" to listOf(
                        mapOf(
                            "type" to "icon",
                            "properties" to listOf(
                                mapOf("name" to "icon", "value" to "Home"),
                            )
                        )
                    ),
                    "unselectedIcon" to listOf(
                        mapOf(
                            "type" to "icon",
                            "properties" to listOf(
                                mapOf("name" to "icon", "value" to "HomeOutline"),
                            )
                        )
                    )
                ),
                mapOf(
                    "type" to "navigationBarItem",
                    "properties" to listOf(
                        mapOf("name" to "index", "value" to "1"),
                        mapOf("name" to "weight", "value" to "1"),
                        mapOf(
                            "name" to "selectedDestination",
                            "value" to "1",
                            "id" to "bottomNavigation.selectedDestination"
                        ),
                    ),
                    "components" to listOf(
                        mapOf(
                            "type" to "text",
                            "properties" to listOf(
                                mapOf("name" to "text", "value" to "Card")
                            )
                        )
                    ),
                    "selectedIcon" to listOf(
                        mapOf(
                            "type" to "icon",
                            "properties" to listOf(
                                mapOf("name" to "icon", "value" to "Payment")
                            )
                        )
                    ),
                    "unselectedIcon" to listOf(
                        mapOf(
                            "type" to "icon",
                            "properties" to listOf(
                                mapOf("name" to "icon", "value" to "PaymentOutline")
                            )
                        )
                    )
                ),
                mapOf(
                    "type" to "navigationBarItem",
                    "properties" to listOf(
                        mapOf("name" to "index", "value" to "2"),
                        mapOf("name" to "weight", "value" to "1"),
                        mapOf(
                            "name" to "selectedDestination",
                            "value" to "2",
                            "id" to "bottomNavigation.selectedDestination"
                        ),
                    ),
                    "components" to listOf(
                        mapOf(
                            "type" to "text",
                            "properties" to listOf(
                                mapOf("name" to "text", "value" to "Investment")
                            )
                        )
                    ),
                    "selectedIcon" to listOf(
                        mapOf(
                            "type" to "icon",
                            "properties" to listOf(
                                mapOf("name" to "icon", "value" to "Investment")
                            )
                        )
                    ),
                    "unselectedIcon" to listOf(
                        mapOf(
                            "type" to "icon",
                            "properties" to listOf(
                                mapOf("name" to "icon", "value" to "InvestmentOutline")
                            )
                        )
                    )
                )
            )
        )
    )

    val topBar = JSONObject(
        mapOf(
            "type" to "topAppBar",
            "properties" to listOf<String>(),
            "components" to listOf(
                mapOf(
                    "type" to "text",
                    "properties" to listOf(
                        mapOf(
                            "name" to "text",
                            "value" to "Home",
                            "id" to "bottomNavigation.selectedDestinationTitle"
                        )
                    )
                )
            )
        )
    )

    val content = JSONObject(
        mapOf(
            "type" to "column",
            "properties" to listOf(
                mapOf("name" to "horizontalFillType", "value" to "Max"),
                mapOf("name" to "horizontalAlignment", "value" to "Center")
            ),
            "components" to listOf(
                mapOf(
                    "type" to "text",
                    "properties" to listOf(
                        mapOf(
                            "name" to "text",
                            "value" to "Salve",
                            "id" to "bottomNavigation.selectedDestinationString"
                        )
                    )
                )
            ),
            "validators" to listOf(
                mapOf(
                    "type" to "intToString",
                    "id" to "bottomNavigation.selectedDestinationString",
                    "data" to mapOf(
                        "0" to "Home Content",
                        "1" to "Card Content",
                        "2" to "Investment Content",
                    ),
                    "required" to listOf("bottomNavigation.selectedDestination")
                ),
                mapOf(
                    "type" to "intToString",
                    "id" to "bottomNavigation.selectedDestinationTitle",
                    "data" to mapOf(
                        "0" to "Home",
                        "1" to "Card",
                        "2" to "Investment",
                    ),
                    "required" to listOf("bottomNavigation.selectedDestination")
                )
            )
        )
    )

    val screen = JSONObject(
        mapOf(
            "type" to "column",
            "properties" to listOf(
                mapOf("name" to "horizontalFillType", "value" to "Max"),
                mapOf("name" to "verticalFillType", "value" to "Max"),
                mapOf("name" to "verticalArrangement", "value" to "SpaceBetween")
            ),
            "components" to listOf(
                topBar,
                content,
                bottomNavigation
            )
        )
    )

    SdUiComponentPreview(jsonObject = screen)
}