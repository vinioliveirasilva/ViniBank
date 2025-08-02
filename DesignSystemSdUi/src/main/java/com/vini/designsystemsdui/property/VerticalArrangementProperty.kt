package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.options.VerticalArrangementOption

class VerticalArrangementProperty(
    val verticalArrangementOption: VerticalArrangementOption = VerticalArrangementOption.Top,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty(
    "verticalArrangement",
    verticalArrangementOption.name,
    id
)