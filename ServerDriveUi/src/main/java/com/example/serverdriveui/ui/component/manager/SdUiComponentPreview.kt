package com.example.serverdriveui.ui.component.manager

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.serverdriveui.di.ServerDriverUiModules
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.vini.designsystem.compose.theme.ViniBankTheme
import org.json.JSONObject
import org.koin.compose.KoinApplication
import org.koin.compose.LocalKoinScope

@Composable
fun SdUiComponentPreview(componentModel: String) {
    KoinApplication(application = { modules(ServerDriverUiModules) }) {
        val scope = LocalKoinScope.current
        val componentParser = remember { ComponentParser(scope) }
        val componentStateManager = remember { ComponentStateManager() }
        val navController = rememberNavController()
        val baseComponent = """ {
            "components": [
                {
                    $componentModel
                }
            ]
        }""".trimIndent()
        ViniBankTheme {
            Column {
                componentParser.parse(
                    data = Gson().fromJson(baseComponent, JsonObject::class.java),
                    componentStateManager = componentStateManager
                ).forEach { it.getComponentAsColumn(navController).invoke(this) }
            }
        }
    }
}

@Composable
fun SdUiComponentPreview(jsonObject: JSONObject) {
    KoinApplication(application = { modules(ServerDriverUiModules) }) {
        val scope = LocalKoinScope.current
        val componentParser = remember { ComponentParser(scope) }
        val componentStateManager = remember { ComponentStateManager() }
        val navController = rememberNavController()
        val baseComponent = """ {
            "components": [
              $jsonObject
            ]
        }""".trimIndent()
        ViniBankTheme {
            Column {
                componentParser.parse(
                    data = Gson().fromJson(baseComponent, JsonObject::class.java),
                    componentStateManager = componentStateManager
                ).forEach { it.getComponentAsColumn(navController).invoke(this) }
            }
        }
    }
}