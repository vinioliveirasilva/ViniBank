package com.vini.designsystemsdui.property

class StageIdentifierProperty(
    val stage: String,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty("stage", stage, id)

