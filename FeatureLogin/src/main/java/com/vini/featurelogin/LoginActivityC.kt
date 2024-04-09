package com.vini.featurelogin

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.vini.featurelogin.ui.theme.ViniBankTheme
import com.vini.featuresignup.SignUpActivity
import org.koin.androidx.compose.koinViewModel

class LoginActivityC : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViniBankTheme {
                LoginUi()
            }
        }
    }

    companion object {
        fun newIntent(context: Context) = Intent(context, LoginActivityC::class.java)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginUi(loginViewModel: LoginViewModel = koinViewModel()) {
    val context = LocalContext.current
    Scaffold(
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
        when(
            val event = loginViewModel.event1.collectAsStateWithLifecycle().value
        ) {
            is LoginUIEvent.OpenSignUp -> context.startActivity(SignUpActivity.newIntent(context))
            is LoginUIEvent.BusinessSuccess -> {
                with(context as Activity) {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
            is LoginUIEvent.ShowAlert -> Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            is LoginUIEvent.Empty -> {}
        }

        Loader(loginViewModel.loaderEvent)
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(dimensionResource(id = com.vini.designsystem.R.dimen.quadruple_grid))
        ) {
            var emailInput by rememberSaveable { mutableStateOf("") }
            var passInput by remember { mutableStateOf("") }

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
                onClick = { loginViewModel.doOnLogin(emailInput, passInput) }
            ) {
                Text(text = stringResource(id = R.string.login_button))
            }

            ElevatedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = dimensionResource(id = com.vini.designsystem.R.dimen.double_grid)),
                onClick = { loginViewModel.openSignUp() }
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
        LoginUi()
    }
}