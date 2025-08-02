package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.BaseComponentProperty
import com.vini.designsystemsdui.property.options.TextAlignOption

class TextAlignProperty(
    val textAlignOption: TextAlignOption = TextAlignOption.Start,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty("textAlign", textAlignOption.name, id)