package com.vini.designsystemsdui.action

import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

fun toStringAction(
    idToChange: String,
    newValue: String,
    id: String? = null,
    name: String? = null,
) = baseAction(
    type = "toString",
    internalId = id,
    internalName = name,
    data = buildJsonObject {
        put("id", idToChange)
        put("value", newValue)
    }
)