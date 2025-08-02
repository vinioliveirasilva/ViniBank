package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.options.VerticalAlignmentOption

class VerticalAlignmentProperty(
    val verticalAlignmentOption: VerticalAlignmentOption = VerticalAlignmentOption.Top,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty(
    "verticalAlignment",
    verticalAlignmentOption.name,
    id
)