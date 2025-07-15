package com.vini.designsystemsdui

fun topBarWithBackAction(title: String) = topBarWithAction(title, "back", "LeftArrow")

fun topBarWithCloseAction(title: String) = topBarWithAction(title, "close", "Close")

private fun topBarWithAction(title: String, action: String, actionIcon: String) =
    ComponentUtil.component(
        "topAppBar",
        listOf(),
        listOf(
            ComponentUtil.component(
                "text",
                listOf(
                    ComponentUtil.property("text", title)
                )
            )
        ),
        validators = listOf(),
        customComponents = arrayOf(
            "navigationIcons" to listOf(
                ComponentUtil.component(
                    "iconButton",
                    action = ComponentUtil.action(action),
                    components = listOf(
                        ComponentUtil.component(
                            "icon",
                            listOf(
                                ComponentUtil.property("icon", actionIcon),
                            )
                        )
                    )
                )
            )
        )
    )