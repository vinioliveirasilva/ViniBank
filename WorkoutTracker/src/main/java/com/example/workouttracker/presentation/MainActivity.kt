package com.example.workouttracker.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.workouttracker.presentation.add.AddWorkoutScreen
import com.example.workouttracker.presentation.home.HomeScreen
import com.example.workouttracker.presentation.run.ExecuteWorkoutScreen
import com.vini.designsystem.compose.theme.ViniBankTheme
import com.vini.designsystem.compose.view.BaseComposeActivity
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.scope.KoinActivityScope

object Routes {

    @Serializable
    data object HOME

    @Serializable
    data class EXECUTE(val name: String)

    @Serializable
    data object ADD
}

class MainActivity : BaseComposeActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KoinActivityScope {
                ViniBankTheme {
                    val navController = rememberNavController()

                    NavHost(navController = navController, startDestination = Routes.HOME) {
                        composable<Routes.HOME> {
                            HomeScreen(
                                onCloseAction = { navController.popBackStack() },
                                onExecuteWorkout = { navController.navigate(Routes.EXECUTE(it)) },
                                onAddWorkout = { navController.navigate(Routes.ADD) },
                            )
                        }

                        composable<Routes.EXECUTE> {
                            val params = it.toRoute<Routes.EXECUTE>()
                            ExecuteWorkoutScreen(params.name)
                        }

                        composable<Routes.ADD> {
                            AddWorkoutScreen {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}