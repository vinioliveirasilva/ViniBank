package com.vini.designsystemsdui.validator

import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator

fun emailValidator(id: String, emails: List<String>) : Validator = ComponentUtil.validator(
    type = "emailValid",
    id = id,
    required = emails
)