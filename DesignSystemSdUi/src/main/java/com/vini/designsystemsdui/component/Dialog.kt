package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Action
import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator
import com.vini.designsystemsdui.property.ShouldShowProperty
import com.vini.designsystemsdui.property.VisibilityProperty

fun dialog(
    shouldShow: ShouldShowProperty = ShouldShowProperty(),
    icon: List<Component> = emptyList(),
    title: List<Component> = emptyList(),
    text: List<Component> = emptyList(),
    visibility: VisibilityProperty = VisibilityProperty(),
    actions: List<Action> = emptyList(),
    validators: List<Validator> = emptyList(),
): Component = ComponentUtil.component(
    "dialog",
    listOf(
        shouldShow.build(),
        visibility.build(),
    ),
    components = emptyList(),
    customComponents = arrayOf(
        "icon" to icon,
        "title" to title,
        "text" to text,
    ),
    actions = actions,
    validators = validators,
)
