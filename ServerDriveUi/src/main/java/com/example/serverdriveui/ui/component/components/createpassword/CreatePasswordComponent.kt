package com.example.serverdriveui.ui.component.components.createpassword

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.components.createpassword.properties.ValidPasswordComponentProperty
import com.example.serverdriveui.ui.component.components.createpassword.properties.ValidPasswordProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.HorizontalPaddingProperty
import com.example.serverdriveui.ui.component.properties.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.TextProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalFillTypeProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingComponentProperty
import com.example.serverdriveui.ui.component.properties.VerticalPaddingProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.google.gson.JsonObject
import com.vini.designsystem.R
import com.vini.designsystem.compose.icon.passwordTrailingIcon
import com.vini.designsystem.compose.visualtransformation.getPasswordVisualTransformation

class CreatePasswordComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val viewModel: CreatePasswordViewModel,
) : BaseComponent(model, validatorParser, stateManager),
    ValidPasswordComponentProperty by ValidPasswordProperty(properties, stateManager),
    TextComponentProperty by TextProperty(properties, stateManager),
    VerticalFillTypeComponentProperty by VerticalFillTypeProperty(properties, stateManager),
    HorizontalFillTypeComponentProperty by HorizontalFillTypeProperty(properties, stateManager),
    VerticalPaddingComponentProperty by VerticalPaddingProperty(properties, stateManager),
    HorizontalPaddingComponentProperty by HorizontalPaddingProperty(properties, stateManager) {

    @Composable
    override fun getComponent(navController: NavHostController): @Composable ColumnScope.() -> Unit =
        {
            val passwordStated by viewModel.uiState.collectAsStateWithLifecycle()
            val confirmPassFocusRequester = remember { FocusRequester() }

            setPasswordValid(passwordStated.isPasswordValid)
            setText(passwordStated.password)

            val defaultModifier = Modifier
                .then(horizontalPaddingModifier)
                .then(verticalPaddingModifier)
                .then(horizontalFillTypeModifier)
                .then(verticalFillTypeModifier)

            OutlinedTextField(
                modifier = Modifier
                    .padding(bottom = dimensionResource(id = R.dimen.double_grid))
                    .then(defaultModifier),
                value = passwordStated.password,
                onValueChange = { viewModel.onEvent(CreatePasswordEvent.DoOnPasswordChange(it)) },
                label = { Text("Senha") },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { confirmPassFocusRequester.requestFocus() }),
                visualTransformation = getPasswordVisualTransformation(passwordStated.isPasswordVisible),
                trailingIcon = passwordTrailingIcon(passwordStated.isPasswordVisible) {
                    viewModel.onEvent(
                        CreatePasswordEvent.DoOnShowPasswordChange(it)
                    )
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .focusRequester(confirmPassFocusRequester)
                    .padding(bottom = dimensionResource(id = R.dimen.double_grid))
                    .then(defaultModifier),
                value = passwordStated.confirmPassword,
                onValueChange = {
                    viewModel.onEvent(CreatePasswordEvent.DoOnConfirmPasswordChange(it))
                },
                label = { Text("Confirmar Senha") },
                singleLine = true,
                visualTransformation = getPasswordVisualTransformation(passwordStated.isPasswordVisible),
                trailingIcon = passwordTrailingIcon(passwordStated.isPasswordVisible) {
                    viewModel.onEvent(
                        CreatePasswordEvent.DoOnShowPasswordChange(it)
                    )
                },
            )

            PasswordValidationText(
                text = "⬤ As senhas devem ser iguais",
                isValid = passwordStated.isPasswordMatch,
                defaultModifier
            )
            PasswordValidationText(
                text = "⬤ A senha deve conter pelo menos 8 caracteres",
                isValid = passwordStated.hasAtLeastEightCharacters,
                defaultModifier
            )
            PasswordValidationText(
                text = "⬤ A senha deve conter pelo menos uma letra maiúscula",
                isValid = passwordStated.hasAtLeastOneUppercaseLetter,
                defaultModifier
            )
            PasswordValidationText(
                text = "⬤ A senha deve conter pelo menos um número",
                isValid = passwordStated.hasAtLeastOneNumber,
                defaultModifier
            )
            PasswordValidationText(
                text = "⬤ A senha deve conter pelo menos um caractere especial",
                isValid = passwordStated.hasAtLeastOneSpecialCharacter,
                defaultModifier
            )
        }

    @Composable
    private fun PasswordValidationText(
        text: String,
        isValid: Boolean,
        modifier: Modifier = Modifier
    ) {
        val color = if (isValid) Color.Green else Color.Red
        Text(
            text = text,
            fontSize = 14.sp,
            color = color,
            fontWeight = FontWeight.Bold,
            modifier = modifier
        )
    }

    companion object {
        const val IDENTIFIER = "createPassword"
    }
}

