package com.vini.designsystemsdui.property

class RequestUpdateProperty(
    val requestUpdate: Boolean,
    val id: String? = null,
) : BaseComponentProperty.BooleanComponentProperty("requestUpdate", requestUpdate, id)

