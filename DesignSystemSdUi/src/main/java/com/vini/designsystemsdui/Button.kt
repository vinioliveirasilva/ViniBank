package com.vini.designsystemsdui

fun button(
    text: String,
    horizontalFillType: String = "Max",
    isEnabled: Boolean = true,
    shape: String = "Small",
) =
    ComponentUtil.component(
        type = "button",
        properties = listOf(
            ComponentUtil.property("text", text),
            ComponentUtil.property("enabled", isEnabled),
            ComponentUtil.property("horizontalFillType", horizontalFillType),
            ComponentUtil.property("shape", shape),
        )
    )

fun outlinedButton(
    text: String,
    horizontalFillType: String = "Max",
    isEnabled: Boolean = true,
    shape: String = "Small",
) =
    ComponentUtil.component(
        type = "outlinedButton",
        properties = listOf(
            ComponentUtil.property("text", text),
            ComponentUtil.property("enabled", isEnabled),
            ComponentUtil.property("horizontalFillType", horizontalFillType),
            ComponentUtil.property("shape", shape),
        )
    )

fun text(text: String) = ComponentUtil.component(
    "text",
    listOf(
        ComponentUtil.property("text", text)
    )
)