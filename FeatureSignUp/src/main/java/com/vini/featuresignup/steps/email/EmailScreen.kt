package com.vini.featuresignup.steps.email

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vini.designsystem.compose.button.Buttons
import com.vini.designsystem.compose.scaffold.BaseScaffold
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.featuresignup.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun EmailScreen(
    onBusinessSuccess: (String) -> Unit,
    viewModel: EmailViewModel = koinViewModel()
) {
    EmailUI(viewModel.uiState, viewModel::handleEvent, onBusinessSuccess)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmailUI(
    uiState: StateFlow<EmailState>,
    handleEvent: (EmailUIEvent) -> Unit,
    onBusinessSuccess: (String) -> Unit = {},
) {
    ViniBankTheme {
        val emailState by uiState.collectAsStateWithLifecycle()
        val snackBarHostState = remember { SnackbarHostState() }
        BaseScaffold(
            snackBarHostState = snackBarHostState,
            topBarTitle = stringResource(id = R.string.email_title),
            buttons = {
                Buttons(
                    primaryIsEnable = emailState.isContinueEnable,
                    primaryAction = { onBusinessSuccess(emailState.email) },
                    primaryText = "Continuar"
                )
            }
        ) {
            val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.lottie_emaill))
            val progress by animateLottieCompositionAsState(
                composition,
                isPlaying = emailState.isContinueEnable,
                restartOnPlay = true
            )

            LottieAnimation(
                modifier = Modifier.height(300.dp),
                composition = composition,
                progress = { progress }
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
                value = emailState.email,
                onValueChange = { handleEvent(EmailUIEvent.OnEmailUpdate(it)) },
                label = { Text("Digite o email") },
                singleLine = true
            )
        }
    }
}

@Preview
@Composable
fun EmailUiPreview() {
    EmailUI(
        uiState = MutableStateFlow(EmailState(isContinueEnable = true, email = "")),
        handleEvent = {}
    )
}