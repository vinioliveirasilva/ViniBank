package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.options.HorizontalArrangementOption

class HorizontalArrangementProperty(
    val horizontalArrangementOption: HorizontalArrangementOption = HorizontalArrangementOption.Start,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty(
    "horizontalArrangement",
    horizontalArrangementOption.name,
    id
)