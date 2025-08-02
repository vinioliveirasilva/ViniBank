package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.options.ShapeOptions

class ShapeProperty(
    val shapeOption: ShapeOptions,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty("shape", shapeOption.name, id)