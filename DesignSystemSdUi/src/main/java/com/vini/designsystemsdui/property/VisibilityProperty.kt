package com.vini.designsystemsdui.property

class VisibilityProperty(
    val isVisible: Boolean = true,
    val id: String? = null,
) : BaseComponentProperty.BooleanComponentProperty("isVisible", isVisible, id)