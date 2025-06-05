package com.example.serverdriveui

import com.google.gson.JsonObject

object SdUiJsonParseExtension {
    fun parseBaseData(data: JsonObject): Pair<String, JsonObject> =
        Pair(data.get("type").asString, data.get("data").asJsonObject)
}