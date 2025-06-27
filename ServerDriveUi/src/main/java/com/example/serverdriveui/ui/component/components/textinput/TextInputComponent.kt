package com.example.serverdriveui.ui.component.components.textinput

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.properties.ErrorComponentProperty
import com.example.serverdriveui.ui.component.properties.ErrorMessageComponentProperty
import com.example.serverdriveui.ui.component.properties.ErrorMessageProperty
import com.example.serverdriveui.ui.component.properties.ErrorProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingProperty
import com.example.serverdriveui.ui.component.properties.KeyboardOptionsComponentProperty
import com.example.serverdriveui.ui.component.properties.KeyboardOptionsProperty
import com.example.serverdriveui.ui.component.properties.LabelComponentProperty
import com.example.serverdriveui.ui.component.properties.LabelProperty
import com.example.serverdriveui.ui.component.properties.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.TextProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingProperty
import com.example.serverdriveui.ui.component.properties.VisualTransformationComponentProperty
import com.example.serverdriveui.ui.component.properties.VisualTransformationProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.asValue
import com.google.gson.JsonObject

data class TextInputComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser
) : BaseComponent(model, validatorParser, stateManager),
    TextComponentProperty by TextProperty(properties, stateManager),
    VerticalFillTypeComponentProperty by VerticalFillTypeProperty(properties, stateManager),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(properties, stateManager),
    VerticalPaddingComponentProperty by VerticalPaddingProperty(properties, stateManager),
    HorizontalPaddingComponentProperty by HorizontalPaddingProperty(properties, stateManager),
    LabelComponentProperty by LabelProperty(properties, stateManager),
    VisualTransformationComponentProperty by VisualTransformationProperty(properties, stateManager),
    KeyboardOptionsComponentProperty by KeyboardOptionsProperty(properties, stateManager),
    ErrorComponentProperty by ErrorProperty(properties, stateManager),
    ErrorMessageComponentProperty by ErrorMessageProperty(properties, stateManager) {

    @Composable
    override fun getComponent(
        navController: NavHostController
    ): @Composable ColumnScope.() -> Unit = {
        val text = getText().asValue()
        val isError = getIsError().asValue()

        TextField(
            keyboardOptions = getKeyboardOptions().asValue(),
            visualTransformation = getVisualTransformation().asValue(),
            singleLine = true,
            isError = isError,
            supportingText = { if (isError) Text(text = getErrorMessage().asValue()) },
            label = { Text(text = getLabel().asValue()) },
            value = text,
            onValueChange = {
                setIsError(false)
                setText(it)
            },
            modifier = Modifier
                .then(horizontalFillTypeModifier)
                .then(verticalFillTypeModifier)
                .then(horizontalPaddingModifier)
                .then(verticalPaddingModifier)
        )
    }

    companion object {
        const val IDENTIFIER = "textInput"
    }
}