package com.example.serverdriveui.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import com.example.serverdriveui.ui.components.properties.static.LabelComponentProperty
import com.example.serverdriveui.ui.components.properties.static.LabelProperty
import com.example.serverdriveui.ui.components.properties.static.PaddingComponentModifier
import com.example.serverdriveui.ui.components.properties.static.PaddingModifier
import com.example.serverdriveui.ui.components.properties.static.TextFormatterComponentProperty
import com.example.serverdriveui.ui.components.properties.static.TextFormatterProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.Validator

data class TextInputComponent(
    private val dynamicProperties: List<PropertyModel>,
    private val staticProperties: Map<String, String>,
    private val stateManager: ComponentStateManager,
    private val validators: List<Validator>,
) : Component,
    TextComponentProperty by TextProperty(dynamicProperties, stateManager),
    FillTypeComponentModifier by FillTypeModifier(staticProperties),
    PaddingComponentModifier by PaddingModifier(staticProperties),
    LabelComponentProperty by LabelProperty(staticProperties),
    TextFormatterComponentProperty by TextFormatterProperty(staticProperties)
{
    init {
        validators.forEach { it.initialize() }
    }

    @Composable
    override fun getComponent(
        navController: NavHostController
    ): @Composable ColumnScope.() -> Unit = {
        val text = getText().collectAsState()

        TextField(
            keyboardOptions = keyboardOptions,
            visualTransformation = visualTransformation,
            singleLine = true,
            isError = false,
            label = { Text(text = label) },
            value = text.value,
            onValueChange = { setText(it) },
            modifier = Modifier
                .then(fillTypeModifier)
                .then(paddingModifier)
        )
    }

    companion object {
        const val IDENTIFIER = "textInput"
    }
}

