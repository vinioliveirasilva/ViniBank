package com.vini.designsystemsdui.property

class TextProperty(
    val text: String,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty("text", text, id)