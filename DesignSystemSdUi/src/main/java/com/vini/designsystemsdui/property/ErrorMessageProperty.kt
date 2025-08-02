package com.vini.designsystemsdui.property

class ErrorMessageProperty(
    val text: String = "",
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty("errorMessage", text, id)