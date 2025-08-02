package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.options.AlignmentOptions

class ContentAlignmentProperty(
    val alignment: AlignmentOptions = AlignmentOptions.TopStart,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty("contentAlignment", alignment.name, id)
