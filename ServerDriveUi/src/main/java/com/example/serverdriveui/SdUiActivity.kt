package com.example.serverdriveui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.router.routes.SdUiRouteData.SdUiRouteDataParser
import com.vini.designsystem.compose.dialog.NonDismissibleDialog
import com.vini.designsystem.compose.loader.LoaderContent
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.designsystem.compose.view.BaseComposeActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@Serializable
data object LoaderRoute

@Serializable
data class SdUiRoute(
    val screenData: String
)

class SdUiActivity : BaseComposeActivity() {

    private val vm: SdUiActivityViewModel by viewModel {
        with(SdUiRouteDataParser(intent)) {
            parametersOf(
                flowId,
                screenData
            )
        }
    }

    @OptIn(KoinExperimentalAPI::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViniBankTheme {
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()

                LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
                    vm.navigateOnSuccess.map {
                        navController.navigate(it) { popUpTo(LoaderRoute) { inclusive = true } }
                    }.launchIn(scope)
                }

                NavHost(
                    navController = navController,
                    startDestination = LoaderRoute,
                    enterTransition = { EnterTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popExitTransition = { ExitTransition.None }
                ) {
                    composable<LoaderRoute> {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .background(Color.White)
                        ) {
                            NonDismissibleDialog { LoaderContent() }
                        }
                    }

                    composable<SdUiRoute> {
                        val routeData = it.toRoute<SdUiRoute>()
                        SdUiScreen(
                            jsonModel = routeData.screenData,
                            navHostController = navController
                        )
                    }
                }
            }
        }
    }

    /*
    (typeMap = mapOf(typeOf<ScreenModel>() to serializableType<ScreenModel>()))


    inline fun <reified T : Any> serializableType(
        isNullableAllowed: Boolean = false,
        json: Gson = Gson(),
    ) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
        override fun get(bundle: Bundle, key: String) =
            bundle.getString(key)?.let { json.fromJson<T>(it, T::class.java) }

        override fun parseValue(value: String): T = json.fromJson<T>(value, T::class.java)

        override fun serializeAsValue(value: T): String = json.toJson(value)

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putString(key, json.toJson(value))
        }
    }

     */
}