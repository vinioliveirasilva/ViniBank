package com.example.serverdriveui.ui.component.components.divider

import androidx.compose.material3.DividerDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.properties.SizeComponentModifier
import com.example.serverdriveui.ui.component.properties.SizeModifier
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.asValue
import com.google.gson.JsonObject

class HorizontalDividerComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser
) : BaseComponent(model, properties, stateManager, validatorParser),
    SizeComponentModifier by SizeModifier(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier
    ): @Composable () -> Unit = {
        HorizontalDivider(
            modifier = modifier,
            thickness = getSize().asValue()?.dp ?: DividerDefaults.Thickness
        )
    }

    companion object {
        const val IDENTIFIER = "horizontalDivider"
    }
}

