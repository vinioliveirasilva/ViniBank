package com.vini.designsystemsdui.validator

import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator

fun allTrueValidator(id: String, toValidate: List<String>) : Validator = ComponentUtil.validator(
    type = "allTrue",
    id = id,
    required = toValidate
)