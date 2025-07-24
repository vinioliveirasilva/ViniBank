package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asInt
import com.vini.common.or

class SizeModifier(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : SizeComponentModifier,
    BasePropertyData<Int?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "size",
        defaultPropertyValue = null,
        transformToData = { it?.asInt() }
    ) {
    override val sizeModifier: Modifier
        @Composable
        get() = getValue()?.let { Modifier.size(it.dp) } or Modifier

    @Composable
    override fun getSize() = getValue()
}