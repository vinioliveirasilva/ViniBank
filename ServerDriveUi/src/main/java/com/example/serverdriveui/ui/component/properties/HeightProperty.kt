package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue

class HeightProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : HeightComponentProperty,
    BasePropertyData<Int?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "height",
        propertyValueTransformation = { it.toInt() },
        defaultPropertyValue = null
    ) {

    override fun getHeight()= getValue()
    override fun setHeight(value: Int) = setValue(value)
    override val heightModifier: Modifier
        @Composable
        get() = getValue().asValue()?.let { Modifier.height(it.dp) } ?: Modifier

}