package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.BaseComponentProperty


class DestinationIndexProperty(
    val index: Int = 0,
    val id: String? = null,
) : BaseComponentProperty.NumberComponentProperty("index", index, id)