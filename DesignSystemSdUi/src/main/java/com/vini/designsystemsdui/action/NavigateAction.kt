package com.vini.designsystemsdui.action

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

fun navigateAction(
    flow: String,
    actionId: String? = null,

    screenData: JsonObject? = null,
    screenRequestData: List<Pair<String, String>> = emptyList(),
    id: String? = null,
    name: String? = null,
) = baseAction(
    type = "navigate",
    internalId = id,
    internalName = name,
    data = buildJsonObject {
        put("flow", flow)
        put("actionId", actionId)
        put(
            "screenRequestData",
            buildJsonObject {
                screenRequestData.forEach {
                    put(it.first, it.second)
                }
            }
        )
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