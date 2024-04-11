package com.vini.featurelogin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vini.common.mvvm.observe
import com.vini.featurelogin.ui.loader.Loader
import com.vini.featurelogin.ui.loader.LoaderState
import com.vini.featurelogin.ui.loader.loaderStateMock
import com.vini.featurelogin.ui.theme.ViniBankTheme
import com.vini.featuresignup.SignUpActivity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.activityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.scope.Scope

class LoginActivity : ComponentActivity(), AndroidScopeComponent {

    override val scope: Scope by activityScope()
    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.vmEvent, ::handleEvent)
        setContent {
            ViniBankTheme {
                LoginUi(
                    viewModel.uiState,
                    viewModel.loaderState,
                    viewModel::handleEvent
                )
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        scope.close()
    }

    private fun handleEvent(event: LoginVMEvent) = when (event) {
        is LoginVMEvent.BusinessSuccess -> {
            setResult(Activity.RESULT_OK)
            finish()
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUi(
    loginState: StateFlow<LoginState>,
    loaderState: StateFlow<LoaderState>,
    eventHandler: (LoginUIEvent) -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.login_title)
                    )
                }
            )
        }
    ) { innerPadding ->
        Loader(loaderState)
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(dimensionResource(id = com.vini.designsystem.R.dimen.quadruple_grid))
        ) {
            val context = LocalContext.current
            val loginStated = loginState.collectAsStateWithLifecycle().value
            var emailInput by rememberSaveable { mutableStateOf(loginStated.email) }
            var passInput by rememberSaveable { mutableStateOf(loginStated.pass) }
            val signUpLauncher = rememberLauncherForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) {
                eventHandler(LoginUIEvent.DoOnSignUp(it.resultCode))
            }

            loginStated.snackBarError?.run {
                LaunchedEffect(null) {
                    scope.launch {
                        snackBarHostState
                            .showSnackbar(
                                message = this@run,
                                duration = SnackbarDuration.Short
                            )

                        eventHandler(LoginUIEvent.DoOnDismissSnackBar)
                    }
                }
            }

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
                value = emailInput,
                onValueChange = { emailInput = it },
                label = { Text(stringResource(id = R.string.login_email_placeholder)) },
                singleLine = true
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
                value = passInput,
                onValueChange = { passInput = it },
                label = { Text(stringResource(id = R.string.login_pass_placeholder)) },
                singleLine = true
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
                onClick = { eventHandler(LoginUIEvent.DoOnLogin(emailInput, passInput)) }
            ) {
                Text(text = stringResource(id = R.string.login_button))
            }

            ElevatedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
                onClick = { signUpLauncher.launch(SignUpActivity.newIntent(context)) }
            ) {
                Text(text = stringResource(id = R.string.signup_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ViniBankTheme {
        LoginUi(
            loginState = MutableStateFlow(LoginState(email = "vini@email.com", pass = "123")),
            loaderState = loaderStateMock(false),
        ) {}
    }
}