package com.vini.designsystemsdui.property

class LottieAnimationDataProperty(
    val animation: String,
    val id: String? = null,
) : BaseComponentProperty.StringComponentProperty("animation", animation, id)
