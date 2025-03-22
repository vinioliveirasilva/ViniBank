package com.vini.featurelogin

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.vini.designsystem.compose.button.Buttons
import com.vini.designsystem.compose.icon.passwordTrailingIcon
import com.vini.designsystem.compose.loader.Loader
import com.vini.designsystem.compose.loader.LoaderState
import com.vini.designsystem.compose.loader.loaderStateMock
import com.vini.designsystem.compose.scaffold.BaseScaffold
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.designsystem.compose.view.BaseComposeActivity
import com.vini.designsystem.compose.visualtransformation.getPasswordVisualTransformation
import com.vini.featuresignup.SignUpActivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseComposeActivity() {

    private val viewModel: LoginViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViniBankTheme {
                LoginUi(
                    viewModel.uiState,
                    viewModel.loaderState,
                    viewModel::handleEvent,
                    viewModel.vmEvent
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}

@Composable
fun LoginUi(
    loginState: StateFlow<LoginState>,
    loaderState: StateFlow<LoaderState>,
    eventHandler: (LoginUIEvent) -> Unit = {},
    loginVMEvent: Flow<LoginVMEvent>
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current as LoginActivity
    val loginStated by loginState.collectAsStateWithLifecycle()
    val signUpLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        eventHandler(LoginUIEvent.DoOnSignUp(it.resultCode))
    }

    LaunchedEffect(loginVMEvent) {
        loginVMEvent.collect {
            when (it) {
                LoginVMEvent.BusinessSuccess -> {
                    context.setResult(Activity.RESULT_OK)
                    context.finish()
                }
            }
        }
    }

    //Validar snackBar
    LaunchedEffect(loginStated.snackBarError) {
        loginStated.snackBarError?.let {
            snackBarHostState.showSnackbar(
                message = it,
                duration = SnackbarDuration.Short,
                withDismissAction = true
            )
            eventHandler(LoginUIEvent.DoOnDismissSnackBar)
        }
    }

    Loader(loaderState)
    BaseScaffold(
        snackBarHostState = snackBarHostState,
        topBarTitle = stringResource(id = R.string.login_title),
        buttons = {
            Buttons(
                primaryAction = { eventHandler(LoginUIEvent.DoOnLogin) },
                primaryText = stringResource(id = R.string.login_button),
                secondaryAction = { signUpLauncher.launch(SignUpActivity.newIntent(context)) },
                secondaryText = stringResource(id = R.string.signup_button)
            )
        }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
            value = loginStated.email,
            onValueChange = { eventHandler(LoginUIEvent.DoOnEmailChange(it)) },
            label = { Text(stringResource(id = R.string.login_email_placeholder)) },
            singleLine = true
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
            value = loginStated.pass,
            onValueChange = { eventHandler(LoginUIEvent.DoOnPassChange(it)) },
            label = { Text(stringResource(id = R.string.login_pass_placeholder)) },
            singleLine = true,
            visualTransformation = getPasswordVisualTransformation(loginStated.isPasswordVisible),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = passwordTrailingIcon(loginStated.isPasswordVisible) {
                eventHandler(
                    LoginUIEvent.DoOnPasswordVisibilityChange(it)
                )
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    ViniBankTheme {
        LoginUi(
            loginState = MutableStateFlow(
                LoginState(
                    email = "vini@email.com",
                    pass = "123",
                    snackBarError = "Error"
                )
            ),
            loaderState = loaderStateMock(false),
            loginVMEvent = MutableStateFlow(LoginVMEvent.BusinessSuccess)
        )
    }
}