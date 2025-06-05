package com.example.serverdriveui.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.components.manager.Component
import com.example.serverdriveui.ui.components.properties.dynamic.TextComponentProperty
import com.example.serverdriveui.ui.components.properties.dynamic.TextProperty
import com.example.serverdriveui.ui.components.properties.static.FillTypeComponentModifier
import com.example.serverdriveui.ui.components.properties.static.FillTypeModifier
import com.example.serverdriveui.ui.components.properties.static.PaddingComponentModifier
import com.example.serverdriveui.ui.components.properties.static.PaddingModifier
import com.example.serverdriveui.ui.components.properties.static.TextAlignComponentProperty
import com.example.serverdriveui.ui.components.properties.static.TextAlignProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.Validator

data class TextComponent(
    private val properties: List<PropertyModel>,
    private val modifiers: Map<String, String>,
    private val stateManager: ComponentStateManager,
    private val validators: List<Validator>,
) : Component,
    TextComponentProperty by TextProperty(properties, stateManager),
    FillTypeComponentModifier by FillTypeModifier(modifiers),
    PaddingComponentModifier by PaddingModifier(modifiers),
    TextAlignComponentProperty by TextAlignProperty(modifiers) {

    init {
        validators.forEach { it.initialize() }
    }

    @Composable
    override fun getComponent(navController: NavHostController): @Composable ColumnScope.() -> Unit =
        {
            val text = getText().collectAsState().value

            Text(
                textAlign = textAlign,
                modifier = Modifier
                    .then(fillTypeModifier)
                    .then(paddingModifier),
                text = text
            )
        }

    companion object {
        const val IDENTIFIER = "text"
    }
}