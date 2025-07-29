package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asInt

data class HorizontalPaddingProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : HorizontalPaddingComponentProperty,
    BasePropertyData<Int>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "paddingHorizontal",
        defaultPropertyValue = 0,
        transformToData = { it?.asInt() }
    ) {
    override val horizontalPaddingModifier: Modifier
        @Composable
        get() = Modifier.padding(horizontal = getValue().dp)
}

interface HorizontalPaddingComponentProperty {
    @get:Composable
    val horizontalPaddingModifier: Modifier
}