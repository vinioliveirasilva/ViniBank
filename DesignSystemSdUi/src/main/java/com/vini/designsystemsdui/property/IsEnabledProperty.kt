package com.vini.designsystemsdui.property

class IsEnabledProperty(
    val isEnabled: Boolean = true,
    val id: String? = null,
) : BaseComponentProperty.BooleanComponentProperty("enabled", isEnabled, id)