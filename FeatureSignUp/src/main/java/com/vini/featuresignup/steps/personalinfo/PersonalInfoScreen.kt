package com.vini.featuresignup.steps.personalinfo

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vini.designsystem.compose.button.Buttons
import com.vini.designsystem.compose.scaffold.BaseScaffold
import com.vini.designsystem.compose.textfield.MaskVisualTransformation
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.featuresignup.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun PersonalInfoScreen(
    onBusinessSuccess: (String) -> Unit,
    viewModel: PersonalInfoViewModel = koinViewModel()
) {
    PersonalInfoUI(
        onBusinessSuccess,
        viewModel.uiState,
        viewModel::handleEvent,
    )
}

@Composable
private fun PersonalInfoUI(
    onBusinessSuccess: (String) -> Unit,
    personalInfoUiState: StateFlow<PersonalInfoUiState>,
    handleEvent: (PersonalInfoEvent) -> Unit,
) {
    ViniBankTheme {
        val snackBarHostState = remember { SnackbarHostState() }
        val docFocusRequester = remember { FocusRequester() }
        val phoneFocusRequester = remember { FocusRequester() }
        val personalInfoState by personalInfoUiState.collectAsStateWithLifecycle()

        BaseScaffold(
            snackBarHostState = snackBarHostState,
            topBarTitle = stringResource(id = R.string.personal_info_title),
            buttons = {
                Buttons(
                    primaryAction = { onBusinessSuccess(personalInfoState.name) },
                    primaryText = stringResource(R.string.personal_info_continue)
                )
            }
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
                value = personalInfoState.name,
                onValueChange = { handleEvent(PersonalInfoEvent.DoOnNameChange(it)) },
                label = { Text(stringResource(R.string.personal_info_name_placeholder)) },
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = { docFocusRequester.requestFocus() }),
            )

            OutlinedTextField(
                modifier = Modifier
                    .focusRequester(docFocusRequester)
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
                value = personalInfoState.document,
                onValueChange = { handleEvent(PersonalInfoEvent.DoOnDocumentChange(it)) },
                label = { Text(stringResource(R.string.personal_info_doc_placeholder)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                visualTransformation = MaskVisualTransformation(
                    mask = "###.###.###-##",
                    toIgnore = '#'
                ),
                keyboardActions = KeyboardActions(onDone = { phoneFocusRequester.requestFocus() }),
            )

            OutlinedTextField(
                modifier = Modifier
                    .focusRequester(phoneFocusRequester)
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
                value = personalInfoState.phone,
                onValueChange = { handleEvent(PersonalInfoEvent.DoOnPhoneChange(it)) },
                label = { Text(stringResource(R.string.personal_info_phone_placeholder)) },
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                visualTransformation = MaskVisualTransformation(
                    mask = "## #####-####",
                    toIgnore = '#'
                ),
            )
        }
    }
}

@Preview
@Composable
fun PreviewPersonalInfo() {
    PersonalInfoUI(
        onBusinessSuccess = { },
        personalInfoUiState = MutableStateFlow(PersonalInfoUiState()),
        handleEvent = { }
    )
}