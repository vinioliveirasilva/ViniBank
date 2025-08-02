package com.vini.designsystemsdui.property

class CurrentPageProperty(
    val page: Int = 0,
    val id: String? = null,
) : BaseComponentProperty.NumberComponentProperty("currentPage", page, id)
