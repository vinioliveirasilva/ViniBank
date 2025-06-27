package com.example.serverdriveui.ui.component.properties

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

class LottieAnimationDataProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : LottieAnimationDataComponentProperty,
    BasePropertyData<LottieCompositionSpec>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "animation",
        propertyValueTransformation = { LottieCompositionSpec.JsonString(it) },
        defaultPropertyValue = LottieCompositionSpec.JsonString("")
    ) {
    override fun getLottieAnimationSpec() = getValue()
    override fun setLottieAnimationSpec(lottieAnimationSpec: LottieCompositionSpec) =
        setValue(lottieAnimationSpec)
}