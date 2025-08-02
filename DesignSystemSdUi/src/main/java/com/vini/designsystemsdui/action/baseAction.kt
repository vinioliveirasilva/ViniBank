package com.vini.designsystemsdui.action

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

fun baseAction(
    type: String,
    internalId: String? = null,
    internalName: String? = null,
    data: JsonObject? = null,
) = buildJsonObject {
    put("type", type)
    put("name", internalName ?: "OnClick")
    internalId?.run { put("id", this) }
    data?.let { put("data", it) }
}