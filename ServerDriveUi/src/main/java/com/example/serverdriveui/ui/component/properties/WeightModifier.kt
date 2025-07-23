package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue
import kotlinx.coroutines.CoroutineScope

class WeightModifier(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : WeightComponentModifier,
    BasePropertyData<Float?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "weight",
        propertyValueTransformation = { it.toFloatOrNull() },
        defaultPropertyValue = "",
        scope = scope
    ) {
    override fun getWeight() = getValue()
    override fun setWeight(value: Float) = setValue(value.toString())
    override val ColumnScope.weightModifier: Modifier
        @Composable
        get() = getWeight().asValue()?.let { Modifier.weight(it) } ?: Modifier

    override val RowScope.weightModifier: Modifier
        @Composable
        get() = getWeight().asValue()?.let { Modifier.weight(it) } ?: Modifier
}