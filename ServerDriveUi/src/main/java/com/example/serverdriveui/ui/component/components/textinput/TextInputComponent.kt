package com.example.serverdriveui.ui.component.components.textinput

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.components.textinput.properties.ErrorComponentProperty
import com.example.serverdriveui.ui.component.components.textinput.properties.ErrorProperty
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.dynamic.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.TextProperty
import com.example.serverdriveui.ui.component.properties.static.FillTypeComponentModifier
import com.example.serverdriveui.ui.component.properties.static.FillTypeModifier
import com.example.serverdriveui.ui.component.properties.static.LabelComponentProperty
import com.example.serverdriveui.ui.component.properties.static.LabelProperty
import com.example.serverdriveui.ui.component.properties.static.PaddingComponentModifier
import com.example.serverdriveui.ui.component.properties.static.PaddingModifier
import com.example.serverdriveui.ui.component.properties.static.TextFormatterComponentProperty
import com.example.serverdriveui.ui.component.properties.static.TextFormatterProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator

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
    TextFormatterComponentProperty by TextFormatterProperty(staticProperties),
    ErrorComponentProperty by ErrorProperty(staticProperties) {

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
            isError = isError,
            supportingText = { if (isError) Text(text = errorMessage) },
            label = { Text(text = label) },
            value = text.value,
            onValueChange = {
                isError = false
                setText(it)
            },
            modifier = Modifier.Companion
                .then(fillTypeModifier)
                .then(paddingModifier)
        )
    }

    companion object {
        const val IDENTIFIER = "textInput"
    }
}