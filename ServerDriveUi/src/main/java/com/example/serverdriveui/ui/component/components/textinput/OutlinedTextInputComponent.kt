package com.example.serverdriveui.ui.component.components.textinput

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.component.properties.ErrorComponentProperty
import com.example.serverdriveui.ui.component.properties.ErrorMessageComponentProperty
import com.example.serverdriveui.ui.component.properties.ErrorMessageProperty
import com.example.serverdriveui.ui.component.properties.ErrorProperty
import com.example.serverdriveui.ui.component.properties.KeyboardOptionsComponentProperty
import com.example.serverdriveui.ui.component.properties.KeyboardOptionsProperty
import com.example.serverdriveui.ui.component.properties.LabelComponentProperty
import com.example.serverdriveui.ui.component.properties.LabelProperty
import com.example.serverdriveui.ui.component.properties.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.TextProperty
import com.example.serverdriveui.ui.component.properties.VisualTransformationComponentProperty
import com.example.serverdriveui.ui.component.properties.VisualTransformationProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.example.serverdriveui.util.asValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.serialization.json.JsonObject

data class OutlinedTextInputComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val componentParser: ComponentParser,
    private val actionParser: ActionParser,
    private val scope: CoroutineScope,
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser, scope),
    TextComponentProperty by TextProperty(properties, stateManager, scope),
    LabelComponentProperty by LabelProperty(properties, stateManager, scope),
    VisualTransformationComponentProperty by VisualTransformationProperty(properties, stateManager, scope),
    KeyboardOptionsComponentProperty by KeyboardOptionsProperty(properties, stateManager, scope),
    ErrorComponentProperty by ErrorProperty(properties, stateManager, scope),
    ErrorMessageComponentProperty by ErrorMessageProperty(properties, stateManager, scope) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier
    ): @Composable () -> Unit = {
        val text = getText().asValue()
        val isError = getIsError().asValue()

        OutlinedTextField(
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
            trailingIcon = {
                componentParser.parseList(model, "trailingIcon").forEach {
                    it.getComponent(navController).invoke()
                }
            },
            modifier = modifier
        )
    }

    companion object {
        const val IDENTIFIER = "outlinedTextInput"
    }
}