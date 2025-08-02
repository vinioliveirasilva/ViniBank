package com.vini.designsystemsdui.component

import com.vini.designsystemsdui.Action
import com.vini.designsystemsdui.Component
import com.vini.designsystemsdui.ComponentUtil
import com.vini.designsystemsdui.Validator
import com.vini.designsystemsdui.property.HeightProperty
import com.vini.designsystemsdui.property.LottieAnimationDataProperty
import com.vini.designsystemsdui.property.VisibilityProperty
import com.vini.designsystemsdui.property.WidthProperty

fun lottieAnimation(
    animationData: LottieAnimationDataProperty = LottieAnimationDataProperty(""),
    height: HeightProperty? = null,
    width: WidthProperty? = null,
    visibility: VisibilityProperty = VisibilityProperty(),
    actions: List<Action> = emptyList(),
    validators: List<Validator> = emptyList(),
): Component = ComponentUtil.component(
    "lottie",
    listOfNotNull(
        animationData.build(),
        height?.build(),
        width?.build(),
        visibility.build(),
    ),
    actions = actions,
    validators = validators,
)
