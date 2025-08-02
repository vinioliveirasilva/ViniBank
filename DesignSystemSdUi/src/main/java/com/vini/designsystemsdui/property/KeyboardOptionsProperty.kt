package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.options.KeyboardOptionsOption

class KeyboardOptionsProperty(
    val keyboardOption: KeyboardOptionsOption = KeyboardOptionsOption.Default,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty(
    "keyboardOptions",
    keyboardOption.name,
    id
)