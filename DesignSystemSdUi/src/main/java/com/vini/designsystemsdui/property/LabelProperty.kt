package com.vini.designsystemsdui.property

class LabelProperty(
    val text: String = "",
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty("label", text, id)