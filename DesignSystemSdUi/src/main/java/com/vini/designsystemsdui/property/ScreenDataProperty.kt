package com.vini.designsystemsdui.property

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

class ScreenDataProperty(
    val screenData: JsonObject,
    val id: String? = null,
) {
    fun build() = buildJsonObject {
        put("name", "screenData")
        put("value", screenData)
        id?.let { put("id", it) }
    }
}

