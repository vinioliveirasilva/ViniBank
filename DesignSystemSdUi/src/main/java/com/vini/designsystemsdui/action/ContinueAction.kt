package com.vini.designsystemsdui.action

import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

fun continueAction(
    flowId: String,
    currentScreenId: String,
    nextScreenId: String = "",

    screenData: JsonObject? = null,
    screenRequestData: List<Pair<String, String>> = emptyList(),
    id: String? = null,
    name: String? = null,
) = baseAction(
    type = "continue",
    internalId = id,
    internalName = name,
    data = buildJsonObject {
        put("flowId", flowId)
        put("currentScreenId", currentScreenId)
        put("nextScreenId", nextScreenId)
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