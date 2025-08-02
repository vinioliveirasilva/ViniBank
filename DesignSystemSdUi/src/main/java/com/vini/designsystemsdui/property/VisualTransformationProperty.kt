package com.vini.designsystemsdui.property

import com.vini.designsystemsdui.property.options.VisualTransformationOption

class VisualTransformationProperty(
    val visualTransformationOption: VisualTransformationOption = VisualTransformationOption.None,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty(
    "visualTransformation",
    visualTransformationOption.name,
    id
)