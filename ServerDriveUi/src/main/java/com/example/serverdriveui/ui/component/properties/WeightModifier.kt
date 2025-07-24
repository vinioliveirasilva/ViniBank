package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asFloat

class WeightModifier(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : WeightComponentModifier,
    BasePropertyData<Float?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "weight",
        transformToData = { it?.asFloat() },
        defaultPropertyValue = null,
    ) {
    override val ColumnScope.weightModifier: Modifier
        @Composable
        get() = getValue()?.let { Modifier.weight(it) } ?: Modifier

    override val RowScope.weightModifier: Modifier
        @Composable
        get() = getValue()?.let { Modifier.weight(it) } ?: Modifier
}