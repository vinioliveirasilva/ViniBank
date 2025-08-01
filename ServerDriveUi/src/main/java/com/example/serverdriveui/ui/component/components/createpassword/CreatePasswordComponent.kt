package com.example.serverdriveui.ui.component.components.createpassword

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
import com.example.serverdriveui.ui.action.manager.ActionParser
import com.example.serverdriveui.ui.component.components.BaseComponent
import com.example.serverdriveui.ui.component.components.createpassword.properties.ValidPasswordComponentProperty
import com.example.serverdriveui.ui.component.components.createpassword.properties.ValidPasswordProperty
import com.example.serverdriveui.ui.component.properties.TextComponentProperty
import com.example.serverdriveui.ui.component.properties.TextProperty
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.ValidatorParser
import com.vini.designsystem.R
import com.vini.designsystem.compose.icon.passwordTrailingIcon
import com.vini.designsystem.compose.visualtransformation.getPasswordVisualTransformation
import kotlinx.serialization.json.JsonObject

class CreatePasswordComponent(
    private val model: JsonObject,
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val validatorParser: ValidatorParser,
    private val viewModel: CreatePasswordViewModel,
    private val actionParser: ActionParser,
) : BaseComponent(model, properties, stateManager, validatorParser, actionParser),
    ValidPasswordComponentProperty by ValidPasswordProperty(properties, stateManager),
    TextComponentProperty by TextProperty(properties, stateManager) {

    @Composable
    override fun getInternalComponent(
        navController: NavHostController,
        modifier: Modifier
    ): @Composable () -> Unit = {
        val passwordStated by viewModel.uiState.collectAsStateWithLifecycle()
        val confirmPassFocusRequester = remember { FocusRequester() }

        setPasswordValid(passwordStated.isPasswordValid)
        setText(passwordStated.password)

        OutlinedTextField(
            modifier = modifier
                .padding(bottom = dimensionResource(id = R.dimen.double_grid)),
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
            modifier = modifier
                .focusRequester(confirmPassFocusRequester)
                .padding(bottom = dimensionResource(id = R.dimen.double_grid)),
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
            modifier
        )
        PasswordValidationText(
            text = "⬤ A senha deve conter pelo menos 8 caracteres",
            isValid = passwordStated.hasAtLeastEightCharacters,
            modifier
        )
        PasswordValidationText(
            text = "⬤ A senha deve conter pelo menos uma letra maiúscula",
            isValid = passwordStated.hasAtLeastOneUppercaseLetter,
            modifier
        )
        PasswordValidationText(
            text = "⬤ A senha deve conter pelo menos um número",
            isValid = passwordStated.hasAtLeastOneNumber,
            modifier
        )
        PasswordValidationText(
            text = "⬤ A senha deve conter pelo menos um caractere especial",
            isValid = passwordStated.hasAtLeastOneSpecialCharacter,
            modifier
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

