package com.example.serverdriveui.ui.component.manager

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.example.serverdriveui.di.ServerDriveUiComponents
import com.example.serverdriveui.di.ServerDriveUiModule
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.vini.designsystem.compose.theme.ViniBankTheme
import org.koin.compose.KoinApplication
import org.koin.compose.LocalKoinScope

@Composable
fun SdUiComponentPreview(jsonModel: String) {
    KoinApplication(application = { modules(ServerDriveUiComponents, ServerDriveUiModule) }) {
        val scope = LocalKoinScope.current
        val componentParser = remember { ComponentParser(scope) }
        val navController = rememberNavController()
        ViniBankTheme {
            Column {
                componentParser.parse(jsonModel.let { Gson().fromJson(it, JsonObject::class.java) })
                    .forEach { it.getComponent(navController).invoke(this) }
            }
        }
    }
}