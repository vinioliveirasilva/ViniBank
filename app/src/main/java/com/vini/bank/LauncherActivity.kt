package com.vini.bank

import android.app.Activity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import com.vini.common.mvvm.observe
import com.vini.designsystem.xml.view.BaseActivity
import com.vini.featurelogin.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class LauncherActivity : BaseActivity(com.vini.designsystem.R.layout.main_content) {

    private val viewModel: LauncherViewModel by viewModel()
    private lateinit var analytics: FirebaseAnalytics

    private val loginLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            goToHome()
        } else {
            finish()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytics = Firebase.analytics
        observe(viewModel.event, ::handleEvent)
        savedInstanceState ?: run {

            val remoteConfig = Firebase.remoteConfig
            val configSettings = remoteConfigSettings {
                minimumFetchIntervalInSeconds = 3600
            }
            remoteConfig.setConfigSettingsAsync(configSettings)
            remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)

            viewModel.doOnCreate()
            analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT) {
                param(FirebaseAnalytics.Param.ITEM_ID, "1")
                param(FirebaseAnalytics.Param.ITEM_NAME, "name")
                param(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
            }
        }
    }

    private fun handleEvent(event: LauncherUIEvent) = when(event) {
        is LauncherUIEvent.OpenHome -> goToHome()
        is LauncherUIEvent.OpenLogin -> goToLogin()
    }

    private fun goToHome() = Toast.makeText(this, "Logado", Toast.LENGTH_SHORT).show()

    private fun goToLogin() = loginLauncher.launch(LoginActivity.newIntent(this))
}