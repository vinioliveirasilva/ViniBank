package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Action
import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator

fun blank(
    actions: List<Action> = emptyList(),
    validators: List<Validator> = emptyList(),
): Component = ComponentUtil.component(
    "blank",
    emptyList(),
    actions = actions,
    validators = validators,
)
