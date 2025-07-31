package com.example.serverdriveui.util

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.booleanOrNull
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.floatOrNull
import kotlinx.serialization.json.intOrNull
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object JsonUtil {
    fun JsonElement.asString(default: String = "") = jsonPrimitive.takeIf { it.isString }?.content ?: default
    fun JsonElement.asInt(default: Int = 0) = jsonPrimitive.intOrNull ?: default
    fun JsonElement.asBoolean(default: Boolean = false) = jsonPrimitive.booleanOrNull ?: default
    fun JsonElement.asFloat(default: Float = 0.0f) = jsonPrimitive.floatOrNull ?: default
    fun JsonObject.getAsMap(name: String, default: Map<String, JsonElement> = emptyMap()) = get(name)?.jsonObject?.toMap() ?: default
    fun JsonObject.getAsStringMap(name: String, default: Map<String, JsonElement> = emptyMap()) = getAsMap(name, default).mapValues { it.value.asString() }
    fun JsonObject.getAsString(name: String, default: String = "") = get(name)?.jsonPrimitive?.content ?: default
    fun JsonObject.getAsNullableString(name: String) = get(name)?.jsonPrimitive?.content
    fun JsonObject.getAsInt(name: String, default: Int = 0) = get(name)?.jsonPrimitive?.intOrNull ?: default
    fun JsonObject.getAsBoolean(name: String) = getAsString(name).toBoolean()
    fun JsonObject.getAsArray(name: String, default: JsonArray = JsonArray(emptyList())) = get(name)?.jsonArray
        ?: default
    fun JsonObject.getAsJsonObjectArray(name: String) = getAsArray(name).map { it.jsonObject }
    fun JsonObject.getAsJsonObject(name: String, default: JsonObject = JsonObject(emptyMap())) = get(name)?.jsonObject ?: default
    inline fun <reified T> JsonObject.getAsTypedArray(name: String) = getAsArray(name).map { Json.Default.decodeFromJsonElement<T>(it) }
}