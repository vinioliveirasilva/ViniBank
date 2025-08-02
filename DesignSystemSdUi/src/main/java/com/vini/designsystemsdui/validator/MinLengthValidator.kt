package com.vini.designsystemsdui.validator

import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put

fun minLengthValidator(id: String, idsToValidate: List<String>, length: Int) : Validator = ComponentUtil.validator(
    type = "minLength",
    id = id,
    required = idsToValidate,
    data = buildJsonObject {
        put("length",length)
    }
)