package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue
import com.vini.common.or

class SizeModifier(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : SizeComponentModifier,
    BasePropertyData<Int?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "size",
        propertyValueTransformation = { it.toIntOrNull() },
        defaultPropertyValue = null
    ) {
    override val sizeModifier: Modifier
        @Composable
        get() = getValue().asValue()?.let { Modifier.size(it.dp) } or Modifier

    override fun getSize() = getValue()
    override fun setSize(size: Int) = setValue(size)
}