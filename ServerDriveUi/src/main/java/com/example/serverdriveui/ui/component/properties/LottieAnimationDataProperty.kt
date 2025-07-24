package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString

class LottieAnimationDataProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : LottieAnimationDataComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "animation",
        defaultPropertyValue = "",
        transformToData = { it?.asString() }
    ) {

    @Composable
    override fun getLottieAnimationStringData() = getValue()
}