package com.example.serverdriveui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import com.example.serverdriveui.di.getNewScopeActivity
import com.example.serverdriveui.ui.action.manager.ActionHandler
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
import org.koin.core.parameter.parametersOf
import org.koin.core.scope.Scope
import java.util.UUID
import kotlin.reflect.typeOf

@Serializable
data object LoaderScreenRoute

@Serializable
data class SdUiScreenRoute(
    val screenData: JsonObject,
)

class SdUiActivity : BaseComposeActivity() {

    override val scope: Scope = getKoin().getNewScopeActivity(UUID.randomUUID().toString())

    private val activityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            actionHandler.handleActivityResult(convertActivityResult(result))
        }
    private val actionHandler: ActionHandler by viewModel()
    private val featureRouter: FeatureRouter by inject { parametersOf(this) }

    private val viewModel: SdUiActivityViewModel by viewModel {
        with(SdUiRouteDataParser(intent)) {
            parametersOf(
                flowId,
                Json.decodeFromString<JsonObject>(screenData)
            )
        }
    }

    override fun onStop() {
        viewModel.doOnStop()
        super.onStop()
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ViniBankTheme {
                val navController = rememberNavController()
                val scope = rememberCoroutineScope()

                LifecycleEventEffect(Lifecycle.Event.ON_CREATE) {
                    viewModel.screenCaller.map {
                        navController.navigate(it) { popUpTo(LoaderScreenRoute) { inclusive = true } }
                    }.launchIn(scope)

                    actionHandler.routeCaller.map {
                        featureRouter.navigate(it, activityResult)
                    }.launchIn(scope)
                }

                NavHost(
                    navController = navController,
                    startDestination = LoaderScreenRoute,
                    enterTransition = { EnterTransition.None },
                    popEnterTransition = { EnterTransition.None },
                    exitTransition = { ExitTransition.None },
                    popExitTransition = { ExitTransition.None }
                ) {
                    composable<LoaderScreenRoute> {
                        Column(
                            Modifier
                                .fillMaxSize()
                                .background(Color.White)
                        ) {
                            NonDismissibleDialog { LoaderContent() }
                        }
                    }

                    composable<SdUiScreenRoute>(
                        typeMap = mapOf(typeOf<JsonObject>() to serializableType<JsonObject>())
                    ) {
                        val routeData = it.toRoute<SdUiScreenRoute>()

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

fun convertActivityResult(activityResult: ActivityResult): Map<String, String> =
    when (activityResult.resultCode) {
        Activity.RESULT_OK -> with(activityResult.data ?: Intent()) {
            extras?.keySet()?.associateWith { getStringExtra(it) ?: "" } ?: emptyMap()
        }

        else -> emptyMap()
    }