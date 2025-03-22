package com.vini.featuresignup.steps.createpassword

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vini.designsystem.compose.button.Buttons
import com.vini.designsystem.compose.icon.passwordTrailingIcon
import com.vini.designsystem.compose.scaffold.BaseScaffold
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.designsystem.compose.visualtransformation.getPasswordVisualTransformation
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

        BaseScaffold(
            snackBarHostState = snackBarHostState,
            topBarTitle = stringResource(id = R.string.create_password_title),
            buttons = {
                Buttons(
                    primaryAction = { onBusinessSuccess(passwordStated.password) },
                    primaryText = stringResource(R.string.create_password_continue),
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
                label = { Text(stringResource(R.string.create_password_password_placeholder)) },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { confirmPassFocusRequester.requestFocus() }),
                visualTransformation = getPasswordVisualTransformation(passwordStated.isPasswordVisible),
                trailingIcon = passwordTrailingIcon(passwordStated.isPasswordVisible) {
                    handleEvent(
                        CreatePasswordEvent.DoOnShowPasswordChange(it)
                    )
                }
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
                label = { Text(stringResource(R.string.confirm_password_password_confirm_placeholder)) },
                singleLine = true,
                visualTransformation = getPasswordVisualTransformation(passwordStated.isPasswordVisible),
                trailingIcon = passwordTrailingIcon(passwordStated.isPasswordVisible) {
                    handleEvent(
                        CreatePasswordEvent.DoOnShowPasswordChange(it)
                    )
                },
            )

            PasswordValidationText(
                text = stringResource(R.string.create_password_same_pass_validation),
                isValid = passwordStated.isPasswordMatch
            )
            PasswordValidationText(
                text = stringResource(R.string.create_password_min_length_pass_validation),
                isValid = passwordStated.hasAtLeastEightCharacters
            )
            PasswordValidationText(
                text = stringResource(R.string.create_password_upper_case_pass_validation),
                isValid = passwordStated.hasAtLeastOneUppercaseLetter
            )
            PasswordValidationText(
                text = stringResource(R.string.create_password_number_pass_validation),
                isValid = passwordStated.hasAtLeastOneNumber
            )
            PasswordValidationText(
                text = stringResource(R.string.create_password_special_pass_validation),
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