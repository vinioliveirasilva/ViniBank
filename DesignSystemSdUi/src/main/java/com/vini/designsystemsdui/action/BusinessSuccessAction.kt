package com.vini.designsystemsdui.action

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject

fun businessSuccessAction(
    screenData: JsonObject? = null,
    id: String? = null,
    name: String? = null,
) = baseAction(
    type = "businessSuccess",
    internalId = id,
    internalName = name,
    data = buildJsonObject {
        put(
            "screenData",
            buildJsonObject {
                screenData?.forEach {
                    put(it.key, it.value)
                }
            }
        )
    }
)