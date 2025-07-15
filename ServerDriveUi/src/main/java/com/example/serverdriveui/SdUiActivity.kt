package com.example.serverdriveui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.router.FeatureRouter
import com.example.router.routes.SdUiRouteData.SdUiRouteDataParser
import com.example.serverdriveui.di.getNewScope
import com.vini.designsystem.compose.dialog.NonDismissibleDialog
import com.vini.designsystem.compose.loader.LoaderContent
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.designsystem.compose.view.BaseComposeActivity
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.compose.LocalKoinScope
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf
import java.util.UUID
import kotlin.reflect.typeOf

@Serializable
data object LoaderRoute

@Serializable
data class SdUiRoute(
    val screenData: JsonObject,
)

class SdUiActivity : BaseComposeActivity() {

    private val featureRouter: FeatureRouter by inject { parametersOf(this) }

    private val vm: SdUiActivityViewModel by viewModel {
        with(SdUiRouteDataParser(intent)) {
            parametersOf(
                flowId,
                Json.decodeFromString<JsonObject>(screenData)
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

                    vm.navigateOnSuccess1.map {
                        featureRouter.navigate(it)
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

                    composable<SdUiRoute>(
                        typeMap = mapOf(typeOf<JsonObject>() to serializableType<JsonObject>())
                    ) {
                        val routeData = it.toRoute<SdUiRoute>()

                        //TODO Melhorar ciclo de vida do scopo
                        val koinScope = remember {
                            getKoin().getNewScope(
                                UUID.randomUUID().toString(),
                                this@SdUiActivity.scope
                            )
                        }
                        CompositionLocalProvider(
                            LocalKoinScope provides koinScope
                        ) {
                            SdUiScreen(
                                jsonModel = routeData.screenData,
                                navHostController = navController
                            )
                        }
                    }
                }
            }
        }
    }


    inline fun <reified T : Any> serializableType(
        isNullableAllowed: Boolean = false,
    ) = object : NavType<T>(isNullableAllowed = isNullableAllowed) {
        override fun get(bundle: Bundle, key: String) =
            bundle.getString(key)?.let { Json.decodeFromString<T>(it) }

        override fun parseValue(value: String): T = Json.decodeFromString<T>(value)

        override fun serializeAsValue(value: T): String = Json.encodeToString(value)

        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }

}