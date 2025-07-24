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
import kotlinx.serialization.json.JsonObject

class BoxComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser),
    ContentAlignmentComponentProperty by ContentAlignmentProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier
    ): @Composable () -> Unit = {
        val actionModifier = actions["OnClick"]?.let { Modifier.clickable{ it.execute(navController) } } ?: Modifier

        Box(
            modifier = modifier
                .then(actionModifier),
            contentAlignment = getContentAlignment(),
        ) {
            componentParser.parseList(data = model).forEach {
                it.getComponent(navController).invoke()
            }
        }
    }

    companion object {
        const val IDENTIFIER = "box"
    }
}