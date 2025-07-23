package com.example.serverdriveui.ui.component.properties

import com.airbnb.lottie.compose.LottieCompositionSpec
import kotlinx.coroutines.flow.StateFlow

interface LottieAnimationDataComponentProperty {
    fun getLottieAnimationSpec() : StateFlow<LottieCompositionSpec>
}