package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.BaseComponentProperty
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption

class HorizontalFillTypeProperty(
    val horizontalFillTypeOption: HorizontalFillTypeOption = HorizontalFillTypeOption.None,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty(
    "horizontalFillType",
    horizontalFillTypeOption.name,
    id
)