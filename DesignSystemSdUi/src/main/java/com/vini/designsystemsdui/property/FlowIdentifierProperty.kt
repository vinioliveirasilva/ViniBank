package com.vini.designsystemsdui.property

class FlowIdentifierProperty(
    val flow: String,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty("flow", flow, id)

