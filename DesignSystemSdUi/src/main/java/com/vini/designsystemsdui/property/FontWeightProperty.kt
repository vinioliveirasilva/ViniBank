package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.options.FontWeightOption

class FontWeightProperty(
    val fontWeightOption: FontWeightOption = FontWeightOption.Default,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty("fontWeight", fontWeightOption.name, id)