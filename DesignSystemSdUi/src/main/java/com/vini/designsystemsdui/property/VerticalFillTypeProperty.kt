package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.BaseComponentProperty
import com.vini.designsystemsdui.property.options.VerticalFillTypeOption

class VerticalFillTypeProperty(
    val verticalFillTypeOption: VerticalFillTypeOption = VerticalFillTypeOption.None,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty(
    "verticalFillType",
    verticalFillTypeOption.name,
    id
)