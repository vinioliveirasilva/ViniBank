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

class SignUpActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Route.EMAIL) {
                composable(Route.EMAIL) {
                    EmailScreen(
                        onBusinessSuccess = { navController.navigate(Route.PERSONAL_INFO) },
                        onBusinessFailure = { this@SignUpActivity.finish() }
                    )
                }
                composable(Route.PERSONAL_INFO) {
                    PersonalInfoScreen(
                        onBusinessSuccess = { navController.navigate(Route.ACCOUNT_TYPE) },
                        onBusinessFailure = { this@SignUpActivity.finish() }
                    )
                }
                composable(Route.ACCOUNT_TYPE) {
                    AccountTypeScreen(
                        onBusinessSuccess = { navController.navigate(Route.CREATE_PASSWORD) },
                        onBusinessFailure = { this@SignUpActivity.finish() }
                    )
                }
                composable(Route.CREATE_PASSWORD) {
                    CreatePasswordScreen(
                        onBusinessSuccess = {
                            this@SignUpActivity.setResult(Activity.RESULT_OK)
                            this@SignUpActivity.finish()
                        },
                        onBusinessFailure = { this@SignUpActivity.finish() }
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