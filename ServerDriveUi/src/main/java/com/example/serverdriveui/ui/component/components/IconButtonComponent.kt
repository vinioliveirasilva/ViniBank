package com.example.serverdriveui.ui.component.components

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject

class IconButtonComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier
    ): @Composable () -> Unit = {
            IconButton(
                onClick = {
                    actionParser.parse(
                        componentJsonModel = model,
                        componentStateManager = stateManager
                    )?.execute(navController)
                }
            ) {
                componentParser.parse(data = model, componentStateManager = stateManager).forEach {
                    it.getComponent(navController).invoke()
                }
            }
        }

    companion object {
        const val IDENTIFIER = "iconButton"
    }
}