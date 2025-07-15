package com.example.serverdriveui.ui.component.manager

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.util.JsonUtil.getAsJsonObjectArray
import com.example.serverdriveui.util.JsonUtil.getAsString
import com.example.serverdriveui.util.JsonUtil.getAsTypedArray
import kotlinx.serialization.json.JsonObject
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named
import org.koin.core.scope.Scope

class ComponentParser(
    private val koinScope: Scope,
) : AutoCloseable {

    private val cache: MutableMap<JsonObject, Component?> = mutableMapOf()

    override fun close() {
        cache.values.forEach { it?.close() }
    }

    fun parseList(
        data: JsonObject,
        componentTag: String = TAG_TO_PARSE,
    ): List<Component> {
        return data.getAsJsonObjectArray(componentTag).map { dataAsJson -> parse(dataAsJson) }
    }

    fun parse(
        data: JsonObject,
    ) = cache[data] ?: koinScope.getOrNull<Component>(named(data.getAsString(TYPE_TAG))) {
        parametersOf(
            data,
            data.getAsTypedArray<PropertyModel>(PROPERTIES_TAG)
                .associateBy { model -> model.name }
        )
    }.also { cache[data] = it } ?: unknownComponent()

    fun unknownComponent() = object : Component {}

    private companion object {
        const val TAG_TO_PARSE = "components"
        const val TYPE_TAG = "type"
        const val PROPERTIES_TAG = "properties"
    }
}