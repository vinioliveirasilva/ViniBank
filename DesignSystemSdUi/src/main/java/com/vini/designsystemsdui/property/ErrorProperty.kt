package com.vini.designsystemsdui.property

class ErrorProperty(
    val isVisible: Boolean = true,
    val id: String? = null,
) : BaseComponentProperty.BooleanComponentProperty("isError", isVisible, id)