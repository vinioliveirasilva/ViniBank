package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.properties.SizeComponentModifier
import com.example.serverdriveui.ui.component.properties.SizeModifier
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.JsonObject

class SpacerComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val actionParser: ActionParser,
    private val scope: CoroutineScope
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser, scope),
    SizeComponentModifier by SizeModifier(properties, stateManager, scope) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
        Spacer(modifier = modifier.then(sizeModifier))
    }

    companion object {
        const val IDENTIFIER = "spacer"
    }
}