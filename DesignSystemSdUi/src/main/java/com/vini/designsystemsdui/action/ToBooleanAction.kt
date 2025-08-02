package com.vini.designsystemsdui.action

import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

fun toBooleanAction(
    idToChange: String,
    newValue: Boolean,
    id: String? = null,
    name: String? = null,
) = baseAction(
    type = "toBoolean",
    internalId = id,
    internalName = name,
    data = buildJsonObject {
        put("id", idToChange)
        put("value", newValue)
    }
)