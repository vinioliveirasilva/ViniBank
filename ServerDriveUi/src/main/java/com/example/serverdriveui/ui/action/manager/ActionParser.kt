package com.example.serverdriveui.ui.action.manager

import androidx.navigation.NavHostController
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.google.gson.JsonObject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class ActionParser(private val koinScope: Scope) {
    fun parse(
        componentJsonModel: JsonObject,
        actionTag: String = "action",
        componentStateManager: ComponentStateManager
    ): Action {
        val actionModel = componentJsonModel.get(actionTag)?.asJsonObject
        val type = actionModel?.get("type")?.asString.orEmpty()
        val data: Map<String, String> = actionModel?.get("data")?.asJsonObject?.asMap().orEmpty()
            .map { it.key to it.value.asString }.toMap()

        return koinScope.getOrNull<Action>(
            named(type)
        ) { parametersOf(data, componentStateManager) } ?: object : Action {
            override fun execute(navController: NavHostController) {}
        }
    }
}