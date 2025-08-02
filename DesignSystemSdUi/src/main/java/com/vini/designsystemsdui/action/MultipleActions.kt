package com.vini.designsystemsdui.action

import com.vini.designsystemsdui.Action
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

fun multipleActions(
    actions: List<Action>,
    id: String? = null,
    name: String? = null,
) = baseAction(
    type = "multipleActions",
    internalId = id,
    internalName = name,
    data = buildJsonObject {
        put(
            "actions",
            buildJsonArray {
                actions.mapIndexed { index, action ->
                    add(
                        buildJsonObject {
                            action.forEach { (key, value) ->
                                put(key, value)
                            }
                            put("name", index)
                        }
                    )
                }
            }
        )
    }
)