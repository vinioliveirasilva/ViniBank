package com.vini.designsystemsdui.action

import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

fun toIntAction(
    idToChange: String,
    newValue: Int,
    id: String? = null,
    name: String? = null,
) = baseAction(
    type = "toInt",
    internalId = id,
    internalName = name,
    data = buildJsonObject {
        put("id", idToChange)
        put("value", newValue)
    }
)