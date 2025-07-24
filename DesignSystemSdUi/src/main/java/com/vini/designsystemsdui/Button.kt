package com.vini.designsystemsdui

fun button(text: String, horizontalFillType: String = "Max", isEnabled: Boolean = true) =
    ComponentUtil.component(
        type = "button",
        properties = listOf(
            ComponentUtil.property("text", text),
            ComponentUtil.property("enabled", isEnabled),
            ComponentUtil.property("horizontalFillType", horizontalFillType),
        )
    )

fun outlinedButton(text: String, horizontalFillType: String = "Max", isEnabled: Boolean = true) =
    ComponentUtil.component(
        type = "outlinedButton",
        properties = listOf(
            ComponentUtil.property("text", text),
            ComponentUtil.property("enabled", isEnabled),
            ComponentUtil.property("horizontalFillType", horizontalFillType),
        )
    )

fun text(text: String) = ComponentUtil.component(
    "text",
    listOf(
        ComponentUtil.property("text", text)
    )
)