package com.example.serverdriveui.ui.component.properties

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

class LottieAnimationDataProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : LottieAnimationDataComponentProperty,
    BasePropertyData<LottieCompositionSpec>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "animation",
        propertyValueTransformation = { LottieCompositionSpec.JsonString(it) },
        defaultPropertyValue = "",
        scope = scope
    ) {
    override fun getLottieAnimationSpec() = getValue()
}