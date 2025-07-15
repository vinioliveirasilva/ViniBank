package com.example.serverdriveui.ui.action.manager

import com.example.serverdriveui.util.JsonUtil.getAsJsonObject
import com.example.serverdriveui.util.JsonUtil.getAsString
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonObject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class ActionParser(private val koinScope: Scope): AutoCloseable {

    private val cache: MutableMap<JsonObject, Action?> = mutableMapOf()

    override fun close() {
        cache.values.forEach { it?.close() }
    }

    fun parse(
        model: JsonObject,
        actionTag: String = TAG_TO_PARSE,
    ): Action? {
        return model[actionTag]?.jsonObject?.let { model ->
            val type = model.getAsString(TYPE_TAG)
            val data = model.getAsJsonObject(DATA_TAG)

            return cache[data] ?: koinScope.getOrNull<Action>(named(type)) { parametersOf(data) }
                .also { cache[data] = it }
        }
    }

    private companion object {
        const val TAG_TO_PARSE = "action"
        const val TYPE_TAG = "type"
        const val DATA_TAG = "data"
    }
}