package com.vini.designsystemsdui.property

class ValidPasswordProperty(
    val isPasswordValid: Boolean,
    val id: String? = null,
) : BaseComponentProperty.BooleanComponentProperty("isPasswordValid", isPasswordValid, id)

