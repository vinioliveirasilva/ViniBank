package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.BaseComponentProperty

class SelectedNavigationDestinationIndexProperty(
    val index: Int = 0,
    val id: String? = null,
) : BaseComponentProperty.NumberComponentProperty("selectedDestination", index, id)