package com.vini.featuresignup.steps.email

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.featuresignup.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.compose.koinViewModel

@Composable
fun EmailScreen(
    onBusinessSuccess: () -> Unit,
    onBusinessFailure: () -> Unit,
    viewModel: EmailViewModel = koinViewModel()
) {
    EmailUI(viewModel.uiState, viewModel::handleEvent, onBusinessSuccess, onBusinessFailure)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmailUI(
    uiState: StateFlow<EmailState>,
    handleEvent: (EmailUIEvent) -> Unit,
    onBusinessSuccess: () -> Unit = {},
    onBusinessFailure: () -> Unit = {},
) {
    ViniBankTheme {
        val context = LocalContext.current
        val emailState by uiState.collectAsStateWithLifecycle()
        val snackBarHostState = remember { SnackbarHostState() }
        val scope = rememberCoroutineScope()
        Scaffold(
            modifier = Modifier.padding(dimensionResource(id = com.vini.designsystem.R.dimen.quadruple_grid)),
            snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
            topBar = {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = stringResource(id = R.string.email_title)
                        )
                    }
                )
            }
        ) { internalPadding ->
            var email by rememberSaveable { mutableStateOf("") }

            Column(
                modifier = Modifier.padding(internalPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
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
                    value = email,
                    onValueChange = {
                        email = it
                        handleEvent(EmailUIEvent.OnEmailUpdate(email))
                    },
                    label = { Text("Digite o email") },
                    singleLine = true
                )

                Button(
                    modifier = Modifier,
                    enabled = emailState.isContinueEnable,
                    onClick = { onBusinessSuccess() }
                ) {
                    Text(text = "Continuar")
                }
            }
        }
    }
}

@Preview
@Composable
fun EmailUiPreview() {
    EmailUI(
        uiState = MutableStateFlow(EmailState(isContinueEnable = true)),
        handleEvent = {}
    )
}