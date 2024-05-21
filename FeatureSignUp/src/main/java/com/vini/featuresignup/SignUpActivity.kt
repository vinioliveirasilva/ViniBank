package com.vini.featuresignup

import android.app.Activity
import android.content.Context
import android.content.Intent
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
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity : BaseComposeActivity() {

    private val viewModel: SignUpViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Route.PERSONAL_INFO) {
                composable(Route.EMAIL) {
                    EmailScreen(
                        onBusinessSuccess = { email ->
                            viewModel.storeEmail(email)
                            navController.navigate(Route.PERSONAL_INFO)
                        }
                    )
                }
                composable(Route.PERSONAL_INFO) {
                    PersonalInfoScreen(
                        onBusinessSuccess = { userName ->
                            viewModel.storeUserName(userName)
                            navController.navigate(Route.CREATE_PASSWORD)
                        }
                    )
                }
                /*
                composable(Route.ACCOUNT_TYPE) {
                    AccountTypeScreen(
                        onBusinessSuccess = { navController.navigate(Route.CREATE_PASSWORD) },
                        onBusinessFailure = { this@SignUpActivity.finish() }
                    )
                }
                 */
                composable(Route.CREATE_PASSWORD) {
                    CreatePasswordScreen(
                        onBusinessSuccess = { password ->
                            viewModel.storePassword(password)

                            this@SignUpActivity.setResult(Activity.RESULT_OK)
                            this@SignUpActivity.finish()
                        }
                    )
                }
            }
        }
    }

    companion object {
        private object Route {
            const val EMAIL = "email"
            const val PERSONAL_INFO = "personal_info"
            const val ACCOUNT_TYPE = "account_type"
            const val CREATE_PASSWORD = "create_password"
        }

        fun newIntent(context: Context) = Intent(context, SignUpActivity::class.java)
    }
}