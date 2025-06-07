package com.vini.featuresignup

import android.app.Activity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vini.designsystem.compose.view.BaseComposeActivity
import com.vini.featuresignup.steps.accounttype.AccountTypeScreen
import com.vini.featuresignup.steps.createpassword.CreatePasswordScreen
import com.vini.featuresignup.steps.email.EmailScreen
import com.vini.featuresignup.steps.personalinfo.PersonalInfoScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.scope.KoinActivityScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseComposeActivity() {

    private val viewModel: SignUpViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KoinActivityScope {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Route.EMAIL) {
                    composable<Route.EMAIL> {
                        EmailScreen(
                            onBusinessSuccess = { email ->
                                viewModel.storeEmail(email)
                                navController.navigate(Route.PERSONAL_INFO)
                            }
                        )
                    }
                    composable<Route.PERSONAL_INFO> {
                        PersonalInfoScreen(
                            onBusinessSuccess = { userName ->
                                viewModel.storeUserName(userName)
                                navController.navigate(Route.ACCOUNT_TYPE)
                            }
                        )
                    }
                    composable<Route.ACCOUNT_TYPE> {
                        AccountTypeScreen(
                            onBusinessSuccess = { navController.navigate(Route.CREATE_PASSWORD) },
                            onBusinessFailure = { this@SignUpActivity.finish() }
                        )
                    }
                    composable<Route.CREATE_PASSWORD> {
                        CreatePasswordScreen(
                            onBusinessSuccess = { password ->
                                viewModel.storePassword(password)

                                this@SignUpActivity.setResult(RESULT_OK)
                                this@SignUpActivity.finish()
                            }
                        )
                    }
                }
            }
        }
    }

    private fun finishWithResult(result: Int = Activity.RESULT_CANCELED) {
        setResult(result)
        finish()
    }

    object Route {
        @Serializable
        data object EMAIL
        @Serializable
        data object PERSONAL_INFO
        @Serializable
        data object ACCOUNT_TYPE
        @Serializable
        data object CREATE_PASSWORD
    }
}