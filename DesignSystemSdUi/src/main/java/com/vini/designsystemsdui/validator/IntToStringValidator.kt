package com.vini.designsystemsdui.validator

import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

fun intToStringValidator(id: String, required: List<String>, intToString: List<Pair<Int, String>>) : Validator = ComponentUtil.validator(
    type = "intToString",
    id = id,
    required = required,
    data = buildJsonObject {
        intToString.forEach { (int, string) ->
            put(int.toString(), string)
        }
    }
)