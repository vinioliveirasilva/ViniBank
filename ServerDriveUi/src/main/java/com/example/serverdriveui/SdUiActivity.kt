package com.example.serverdriveui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.router.routes.SdUiRouteDataParser
import com.example.serverdriveui.ui.validator.Validator
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.designsystem.compose.view.BaseComposeActivity
import kotlinx.serialization.Serializable
import org.koin.android.ext.android.getKoin
import org.koin.androidx.compose.scope.KoinActivityScope

@Serializable
data class SDUI(val screenId: String, val screenData: String = "{}")

class SdUiActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(SdUiRouteDataParser(intent)) {
            setContent {
                KoinActivityScope {
                    ViniBankTheme {
                        val navController = rememberNavController()

                        NavHost(navController = navController, startDestination = SDUI(screenId)) {
                            composable<SDUI> {
                                val routeData = it.toRoute<SDUI>()
                                SdUiScreen(
                                    screenId = routeData.screenId,
                                    screenData = routeData.screenData,
                                    navHostController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        getKoin().getAll<Validator>().forEach {
            it.shutdown()
        }
    }
}