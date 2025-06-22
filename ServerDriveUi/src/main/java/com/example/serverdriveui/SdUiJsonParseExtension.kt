package com.example.serverdriveui

import com.example.serverdriveui.service.model.ComponentModel
import com.example.serverdriveui.service.model.ScreenModel
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken

object SdUiJsonParseExtension {
    fun parseBaseData(data: JsonObject): Pair<String, JsonObject> =
        Pair(data.get("type").asString, data.get("data").asJsonObject)

    fun getScreenModelFromJson(data: JsonObject): ScreenModel {
        val componentType = object : TypeToken<List<ComponentModel>>() {}.type
        val shouldCache: Boolean = data.get("shouldCache").asBoolean
        val flow = data.get("flow").asString
        val stage = data.get("stage").asString
        val template = data.get("template").asString
        val version = data.get("version").asString
        val components: List<ComponentModel> =
            Gson().fromJson(data.get("components").asJsonArray, componentType)

        return ScreenModel(
            shouldCache = shouldCache,
            flow = flow,
            stage = stage,
            template = template,
            version = version,
            components = components
        )
    }
}