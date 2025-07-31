package com.vini.bank

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.router.FeatureRouter
import com.example.router.routes.SdUiRoute
import com.example.router.routes.SdUiRouteData
import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.analytics.logEvent
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.google.firebase.remoteconfig.remoteConfig
import com.vini.common.mvvm.observe
import com.vini.designsystem.compose.view.BaseComposeActivity
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class LauncherActivity : BaseComposeActivity() {

    private val viewModel: LauncherViewModel by viewModel()
    private val featureRouter: FeatureRouter by inject { parametersOf(this) }
    private lateinit var analytics: FirebaseAnalytics

    private val loginLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { viewModel.doOnLoginRouteResult(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        analytics = Firebase.analytics
        observe(viewModel.event, ::handleEvent)

        setContent {
            Row(modifier = Modifier
                .fillMaxSize()
                .background(Color.White)) {
                val composition by rememberLottieComposition(
                    LottieCompositionSpec.RawRes(
                        com.vini.designsystem.R.raw.lottie_launcher
                    )
                )

                val progress by animateLottieCompositionAsState(
                    composition,
                    isPlaying = viewModel.uiState.collectAsState().value,
                    restartOnPlay = true
                )

                LottieAnimation(
                    modifier = Modifier.fillMaxSize(),
                    composition = composition,
                    progress = { progress }
                )
            }
        }

        savedInstanceState ?: viewModel.doOnCreate()//setupRemoteConfig() TODO
    }

    private fun setupRemoteConfig() {
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 60
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config_defaults)
        remoteConfig.fetchAndActivate().addOnCompleteListener {
            println(remoteConfig.getBoolean("abc"))
        }

        analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
            param(FirebaseAnalytics.Param.ITEM_ID, "1")
            param(FirebaseAnalytics.Param.ITEM_NAME, "name")
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "image")
        }
    }

    private fun handleEvent(event: LauncherUIEvent) = when (event) {
        is LauncherUIEvent.OpenHome -> featureRouter.navigateAndFinish(SdUiRoute(SdUiRouteData.StartAsDefault(flowId = "Home")))
        is LauncherUIEvent.OpenLogin -> featureRouter.navigate(SdUiRoute(SdUiRouteData.StartAsDefault(flowId = "Login")), loginLauncher)
        is LauncherUIEvent.Finish -> finish()
    }
}