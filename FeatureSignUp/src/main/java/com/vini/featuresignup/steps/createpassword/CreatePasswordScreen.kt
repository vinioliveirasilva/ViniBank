package com.vini.featuresignup.steps.createpassword

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vini.designsystem.compose.button.Buttons
import com.vini.designsystem.compose.scaffold.BaseScaffold
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.featuresignup.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun CreatePasswordScreen(
    onBusinessSuccess: (String) -> Unit,
    viewModel: CreatePasswordViewModel = koinViewModel()
) {
    CreatePasswordUI(
        onBusinessSuccess,
        viewModel.uiState,
        viewModel::onEvent
    )
}

@Composable
private fun CreatePasswordUI(
    onBusinessSuccess: (String) -> Unit,
    passwordState: StateFlow<CreatePasswordState>,
    handleEvent: (CreatePasswordEvent) -> Unit = {}
) {
    ViniBankTheme {
        val snackBarHostState = remember { SnackbarHostState() }
        val passwordStated by passwordState.collectAsStateWithLifecycle()
        val confirmPassFocusRequester = remember { FocusRequester() }
        var showPassword by remember { mutableStateOf(false) }

        BaseScaffold(
            snackBarHostState = snackBarHostState,
            topBarTitle = stringResource(id = R.string.create_password_title),
            buttons = {
                Buttons(
                    primaryAction = { onBusinessSuccess(passwordStated.password) },
                    primaryText = "Continuar",
                    primaryIsEnable = passwordStated.isContinueEnabled
                )
            }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
                value = passwordStated.password,
                onValueChange = { handleEvent(CreatePasswordEvent.DoOnPasswordChange(it)) },
                label = { Text("Senha") },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { confirmPassFocusRequester.requestFocus() }),
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    if (showPassword) {
                        IconButton(
                            onClick = { showPassword = false }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPassword = true }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                },
            )

            OutlinedTextField(
                modifier = Modifier
                    .focusRequester(confirmPassFocusRequester)
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
                value = passwordStated.confirmPassword,
                onValueChange = {
                    handleEvent(CreatePasswordEvent.DoOnConfirmPasswordChange(it))
                },
                label = { Text("Confirmar Senha") },
                singleLine = true,
                visualTransformation = if (showPassword) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                },
                trailingIcon = {
                    if (showPassword) {
                        IconButton(
                            onClick = { showPassword = false }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Visibility,
                                contentDescription = "hide_password"
                            )
                        }
                    } else {
                        IconButton(
                            onClick = { showPassword = true }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.VisibilityOff,
                                contentDescription = "hide_password"
                            )
                        }
                    }
                },
            )

            PasswordValidationText(
                text = "⬤ As senhas devem ser iguais",
                isValid = passwordStated.isPasswordMatch
            )
            PasswordValidationText(
                text = "⬤ A senha deve conter pelo menos 8 caracteres",
                isValid = passwordStated.hasAtLeastEightCharacters
            )
            PasswordValidationText(
                text = "⬤ A senha deve conter pelo menos uma letra maiúscula",
                isValid = passwordStated.hasAtLeastOneUppercaseLetter
            )
            PasswordValidationText(
                text = "⬤ A senha deve conter pelo menos um número",
                isValid = passwordStated.hasAtLeastOneNumber
            )
            PasswordValidationText(
                text = "⬤ A senha deve conter pelo menos um caractere especial",
                isValid = passwordStated.hasAtLeastOneSpecialCharacter
            )
        }
    }
}

@Composable
private fun PasswordValidationText(text: String, isValid: Boolean, modifier: Modifier = Modifier) {
    val color = if (isValid) Color.Green else Color.Red
    Text(
        text = text,
        fontSize = 14.sp,
        color = color,
        fontWeight = FontWeight.Bold,
        modifier = modifier
    )
}

@Preview
@Composable
fun PreviewCreatePasswordUI() {
    CreatePasswordUI(
        onBusinessSuccess = { },
        passwordState = MutableStateFlow(
            CreatePasswordState(
                password = "12345678",
                confirmPassword = "12345678",
            )
        )
    )
}