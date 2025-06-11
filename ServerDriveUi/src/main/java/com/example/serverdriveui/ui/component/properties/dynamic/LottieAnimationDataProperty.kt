package com.example.serverdriveui.ui.component.properties.dynamic

import com.airbnb.lottie.compose.LottieCompositionSpec
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.static.PropertyBaseData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LottieAnimationDataProperty(
    private val properties: List<PropertyModel>,
    private val stateManager: ComponentStateManager,
) : LottieAnimationDataComponentProperty, PropertyBaseData(properties, "animation") {

    private val parsedValue = LottieCompositionSpec.JsonString(propertyValue.orEmpty())
    private lateinit var stateFlow: MutableStateFlow<LottieCompositionSpec>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<LottieCompositionSpec>(parsedValue) },
            notNull = { stateManager.registerState<LottieCompositionSpec>(it, parsedValue) }
        )
    }


    override fun getLottieAnimationSpec(): StateFlow<LottieCompositionSpec> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<LottieCompositionSpec>(it) ?: stateFlow }
        )
    }

    override fun setLottieAnimationSpec(lottieAnimationSpec: LottieCompositionSpec) {
        propertyId.runWhen(
            isNull = { stateFlow.update { lottieAnimationSpec } },
            notNull = { stateManager.updateState<LottieCompositionSpec>(it, lottieAnimationSpec) }
        )
    }
}