package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.BaseComponentProperty
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption

class HorizontalAlignmentProperty(
    val horizontalAlignmentOption: HorizontalAlignmentOption = HorizontalAlignmentOption.Start,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty(
    "horizontalAlignment",
    horizontalAlignmentOption.name,
    id
)