package com.example.serverdriveui.ui.component.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.static.SizeComponentModifier
import com.example.serverdriveui.ui.component.properties.static.SizeModifier
import com.example.serverdriveui.ui.validator.manager.Validator

class SpacerComponent(
    private val staticProperties: Map<String, String>,
    private val validators: List<Validator>,
) : Component,
    SizeComponentModifier by SizeModifier(staticProperties)
{

    init {
        validators.forEach { it.initialize() }
    }

    @Composable
    override fun getComponent(navController: NavHostController): @Composable ColumnScope.() -> Unit = {
        Spacer(modifier = Modifier.then(sizeModifier))
    }

    companion object {
        const val IDENTIFIER = "spacer"
    }
}

