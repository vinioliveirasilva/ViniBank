package com.vini.designsystemsdui.property

class ErrorProperty(
    val isError: Boolean = false,
    val id: String? = null,
) : BaseComponentProperty.BooleanComponentProperty("isError", isError, id)