package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.BaseComponentProperty

class HeightProperty(
    val height: Int,
    val id: String? = null,
) : BaseComponentProperty.NumberComponentProperty("height", height, id)