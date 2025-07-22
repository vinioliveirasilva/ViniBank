package com.example.serverdriveui.ui.component.components.textinput

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.components.BaseComponent
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
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.JsonObject

data class TextInputComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val actionParser: ActionParser,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser),
    TextComponentProperty by TextProperty(properties, stateManager),
    LabelComponentProperty by LabelProperty(properties, stateManager),
    VisualTransformationComponentProperty by VisualTransformationProperty(properties, stateManager, scope),
    KeyboardOptionsComponentProperty by KeyboardOptionsProperty(properties, stateManager),
    ErrorComponentProperty by ErrorProperty(properties, stateManager),
    ErrorMessageComponentProperty by ErrorMessageProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier,
    ): @Composable () -> Unit = {
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
            modifier = modifier
        )
    }

    companion object {
        const val IDENTIFIER = "textInput"
    }
}