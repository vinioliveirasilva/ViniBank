package com.example.serverdriveui.ui.component.manager

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.example.serverdriveui.SdUiUI
import com.example.serverdriveui.di.ServerDriverUiModules
import com.example.serverdriveui.di.getNewScope
import com.example.serverdriveui.di.getNewScopeActivity
import com.vini.designsystem.compose.loader.loaderStateMock
import kotlinx.serialization.json.JsonObject
import org.koin.compose.KoinApplication
import org.koin.compose.LocalKoinScope
import org.koin.compose.getKoin
import org.koin.compose.getKoinScope
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.parameter.parametersOf
import java.util.UUID

@OptIn(KoinInternalApi::class)
@Composable
fun SdUiComponentPreview(jsonObject: JsonObject) {
    KoinApplication(
        application = {
            modules(ServerDriverUiModules)
        }
    ) {
        val koinScope = getKoin().getNewScope(
            UUID.randomUUID().toString(),
            getKoin().getNewScopeActivity(UUID.randomUUID().toString())
        )
        CompositionLocalProvider(
            LocalKoinScope provides koinScope
        ) {
            val navController = rememberNavController()
            val scope = getKoinScope()
            val componentParser = scope.get<ComponentParser> { parametersOf(scope) }
            SdUiUI(
                listOf(componentParser.parse(jsonObject)),
                loaderStateMock(false),
                navController
            )
        }
    }
}