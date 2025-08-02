package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Action
import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator
import com.vini.designsystemsdui.property.TextProperty
import com.vini.designsystemsdui.property.VisibilityProperty

fun snackBar(
    text: TextProperty = TextProperty(""),
    visibility: VisibilityProperty = VisibilityProperty(),
    actions: List<Action> = emptyList(),
    validators: List<Validator> = emptyList(),
): Component = ComponentUtil.component(
    "snackBar",
    listOf(
        text.build(),
        visibility.build(),
    ),
    actions = actions,
    validators = validators,
)
