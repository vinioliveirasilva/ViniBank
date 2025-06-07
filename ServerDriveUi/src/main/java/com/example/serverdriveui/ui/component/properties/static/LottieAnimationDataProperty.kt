package com.example.serverdriveui.ui.component.properties.static

import com.airbnb.lottie.compose.LottieCompositionSpec

class LottieAnimationDataProperty(modifiers: Map<String, String>) : LottieAnimationDataComponentProperty {
    override val lottieAnimationSpec: LottieCompositionSpec = LottieCompositionSpec.JsonString(modifiers["animation"].orEmpty())
}