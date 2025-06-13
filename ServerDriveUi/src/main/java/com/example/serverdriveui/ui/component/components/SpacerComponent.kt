package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.actions.manager.Action
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.dynamic.SizeComponentModifier
import com.example.serverdriveui.ui.component.properties.dynamic.SizeModifier
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator

class SpacerComponent(
    private val dynamicProperties: List<PropertyModel>,
    private val validators: List<Validator>,
    private val action: Action,
    private val stateManager: ComponentStateManager,
) : Component,
    SizeComponentModifier by SizeModifier(dynamicProperties, stateManager)
{

    init {
        validators.forEach { it.initialize() }
    }

    @Composable
    override fun getComponent(navController: NavHostController): @Composable LazyListScope.() -> Unit = {
        Spacer(modifier = Modifier.then(sizeModifier))
    }

    companion object {
        const val IDENTIFIER = "spacer"
    }
}

