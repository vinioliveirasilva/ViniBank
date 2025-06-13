package com.example.serverdriveui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.router.routes.SdUiRouteDataParser
import com.example.serverdriveui.service.model.ScreenModel
import com.google.gson.Gson
import com.vini.designsystem.compose.dialog.NonDismissibleDialog
import com.vini.designsystem.compose.loader.LoaderContent
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.designsystem.compose.view.BaseComposeActivity
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.scope.KoinActivityScope
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import kotlin.reflect.typeOf

@Serializable
data object LoaderDestination

@Serializable
data class SdUiDestination(
    val flowId: String,
    val screenId: String = "",
    val screenData: String = "{}",
    val lastScreenId: String = "",
)

@Serializable
data class SdUiDestination2(
    val screenData: ScreenModel
)

class SdUiActivity : BaseComposeActivity() {

    private val vm: SdUiActivityViewModel by viewModel { parametersOf(SdUiRouteDataParser(intent).flowId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(SdUiRouteDataParser(intent)) {
            setContent {
                KoinActivityScope {
                    ViniBankTheme {
                        val navController = rememberNavController()
                        val scope = rememberCoroutineScope()

                        LaunchedEffect(true) {
                            println("LaunchedEffect")
                            scope.launch {
                                vm.navigateOnSuccess.collect {
                                    println("navigateSuccess")
                                    navController.navigate(it) {
                                        popUpTo(LoaderDestination) {
                                            saveState = false
                                            inclusive = true
                                        }
                                        restoreState = true
                                    }
                                }
                            }
                        }

                        println("criando navHost")
                        NavHost(
                            navController = navController,
                            startDestination = LoaderDestination
                        ) {
                            composable<LoaderDestination>(
                                exitTransition = { ExitTransition.None },
                                popExitTransition = { ExitTransition.None }
                            ) {

                                println("loader")
                                Column(Modifier
                                    .fillMaxSize()
                                    .background(Color.White)) {
                                    NonDismissibleDialog { LoaderContent() }
                                }
                            }

                            composable<SdUiDestination2>(
                                typeMap = mapOf(typeOf<ScreenModel>() to serializableType<ScreenModel>()),
                                enterTransition = { EnterTransition.None },
                                popEnterTransition = { EnterTransition.None },
                                exitTransition = { ExitTransition.None },
                                popExitTransition = { ExitTransition.None },
                            ) {
                                println("iniciando composicao")
                                val routeData = it.toRoute<SdUiDestination2>()
                                SdUiScreen2(
                                    model = routeData.screenData,
                                    navHostController = navController
                                )
                            }
                        }
                    }
                }
            }
        }
    }

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
}