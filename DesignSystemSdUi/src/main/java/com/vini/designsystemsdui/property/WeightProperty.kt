package com.vini.designsystemsdui.property

class WeightProperty(
    val weight: Int,
    val id: String? = null,
) : BaseComponentProperty.NumberComponentProperty("weight", weight, id)