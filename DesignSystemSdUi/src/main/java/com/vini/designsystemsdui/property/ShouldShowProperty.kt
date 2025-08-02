package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.BaseComponentProperty

class ShouldShowProperty(
    val shouldShow: Boolean = false,
    val id: String? = null,
) : BaseComponentProperty.BooleanComponentProperty("shouldShow", shouldShow, id)