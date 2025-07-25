package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.properties.ContentAlignmentComponentProperty
import com.example.serverdriveui.ui.component.properties.ContentAlignmentProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.asValue
import com.google.gson.JsonObject

class BoxComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser),
    ContentAlignmentComponentProperty by ContentAlignmentProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier
    ): @Composable () -> Unit = {
        val action = actionParser.parse(model, componentStateManager = stateManager)
        val actionModifier = action?.let { Modifier.clickable{ it.execute(navController) } } ?: Modifier

        Box(
            modifier = modifier
                .then(actionModifier),
            contentAlignment = getContentAlignment().asValue(),
        ) {
            componentParser.parseList(data = model, componentStateManager = stateManager).forEach {
                it.getComponent(navController).invoke()
            }
        }
    }

    companion object {
        const val IDENTIFIER = "box"
    }
}