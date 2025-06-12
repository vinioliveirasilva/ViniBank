package com.example.serverdriveui.ui.component.components.textinput

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.actions.manager.Action
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.properties.dynamic.ErrorComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.ErrorMessageComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.ErrorMessageProperty
import com.example.serverdriveui.ui.component.properties.dynamic.ErrorProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.HorizontalPaddingProperty
import com.example.serverdriveui.ui.component.properties.dynamic.KeyboardOptionsComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.KeyboardOptionsProperty
import com.example.serverdriveui.ui.component.properties.dynamic.LabelComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.LabelProperty
import com.example.serverdriveui.ui.component.properties.dynamic.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.TextProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalPaddingProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VisualTransformationComponentProperty
import com.example.serverdriveui.ui.component.properties.dynamic.VisualTransformationProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator
import com.example.serverdriveui.util.asValue

data class TextInputComponent(
    private val dynamicProperties: List<PropertyModel>,
    private val validators: List<Validator>,
    private val action: Action,
    private val stateManager: ComponentStateManager,
) : Component,
    TextComponentProperty by TextProperty(dynamicProperties, stateManager),
    VerticalFillTypeComponentProperty by VerticalFillTypeProperty(dynamicProperties, stateManager),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(dynamicProperties, stateManager),
    VerticalPaddingComponentProperty by VerticalPaddingProperty(dynamicProperties, stateManager),
    HorizontalPaddingComponentProperty by HorizontalPaddingProperty(dynamicProperties, stateManager),
    LabelComponentProperty by LabelProperty(dynamicProperties, stateManager),
    VisualTransformationComponentProperty by VisualTransformationProperty(dynamicProperties, stateManager),
    KeyboardOptionsComponentProperty by KeyboardOptionsProperty(dynamicProperties, stateManager),
    ErrorComponentProperty by ErrorProperty(dynamicProperties, stateManager),
    ErrorMessageComponentProperty by ErrorMessageProperty(dynamicProperties, stateManager) {

    init {
        validators.forEach { it.initialize() }
    }

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

@Preview
@Composable
fun TextInputComponentPreview() {
    val navHostController = NavHostController(LocalContext.current)
    Column(modifier = Modifier.fillMaxSize()) {
        TextInputComponent(
            listOf(
                PropertyModel(name = "label", value = "Label"),
                PropertyModel(name = "text", value = "")
            ),
            emptyList(),
            object : Action { override fun execute(navController: NavHostController) {} },
            ComponentStateManager(),
        ).getComponent(navController = navHostController).invoke(this)
    }
}