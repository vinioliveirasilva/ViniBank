package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asInt

class WidthProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : WidthComponentProperty,
    BasePropertyData<Int?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "width",
        transformToData = { it?.asInt() },
        defaultPropertyValue = null,
    ) {
    override val widthModifier: Modifier
        @Composable
        get() = getValue()?.let { Modifier.width(it.dp) } ?: Modifier

}

interface WidthComponentProperty {
    @get:Composable
    val widthModifier: Modifier
}