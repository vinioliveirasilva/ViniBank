package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject

data class CardComponent(
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
        modifier: Modifier,
    ): @Composable () -> Unit = {

        val action = actionParser.parse(model, componentStateManager = stateManager)
        val hasAction = action != null

        Card(
            modifier = modifier.clickable(enabled = hasAction) { action?.execute(navController) },
        ) {
            componentParser.parse(model, componentStateManager = stateManager).forEach {
                it.getComponentAsColumn(navController).invoke(this)
            }
        }
    }

    companion object {
        const val IDENTIFIER = "card"
    }
}