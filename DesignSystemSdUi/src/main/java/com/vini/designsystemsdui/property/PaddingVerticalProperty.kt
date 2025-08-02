package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.BaseComponentProperty

class PaddingVerticalProperty(
    val padding: Int = 0,
    val id: String? = null,
) : BaseComponentProperty.NumberComponentProperty("paddingVertical", padding, id)