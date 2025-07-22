package com.example.serverdriveui.ui.action.manager

import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.getAsJsonObject
import com.example.serverdriveui.util.JsonUtil.getAsJsonObjectArray
import com.example.serverdriveui.util.JsonUtil.getAsNullableString
import com.example.serverdriveui.util.JsonUtil.getAsString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch
import kotlinx.serialization.json.JsonObject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class ActionParser(
    private val koinScope: Scope,
    private val componentStateManager: ComponentStateManager,
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
) : AutoCloseable {

    private val cache: MutableMap<JsonObject, Action?> = mutableMapOf()

    override fun close() {
        scope.cancel()
    }

    fun parseActions(
        model: JsonObject,
    ): Map<String, Action> {
        return model.getAsJsonObjectArray("actions").mapNotNull { action ->
            parseAction(action)
        }.associate { it.first to it.second }
    }

    private fun parseAction(
        model: JsonObject,
    ): Pair<String, Action>? {
        val type = model.getAsString("type")
        val id = model.getAsNullableString("id")
        val name = model.getAsString("name")
        val data = model.getAsJsonObject("data")

        return (cache[data] ?: koinScope.getOrNull<Action>(named(type)) { parametersOf(data) })
            ?.let { action ->
                id?.initializeAutoTrigger(action)
                cache[data] = action
                name to action
            }
    }

    private fun String.initializeAutoTrigger(action: Action) {
        componentStateManager.registerState(this, null)
        scope.launch {
            componentStateManager
                .getState<String>(this@initializeAutoTrigger)
                ?.filterNotNull()
                ?.collect { action.execute() }
        }
    }
}