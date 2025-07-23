package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

class HorizontalArrangementProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : HorizontalArrangementComponentProperty,
    BasePropertyData<Arrangement.Horizontal>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalArrangement",
        propertyValueTransformation = { it.toHorizontalArrangement() },
        defaultPropertyValue = HorizontalArrangementOptions.Start.id,
        scope = scope
    ) {
    override fun getHorizontalArrangement() = getValue()
    override fun setHorizontalArrangement(value: HorizontalArrangementOptions) = setValue(value.id)
}

enum class HorizontalArrangementOptions(val id: String, val arrangement: Arrangement.Horizontal) {
    Start("Start", Arrangement.Start),
    End("End", Arrangement.End),
    Center("Center", Arrangement.Center),
    SpaceBetween("SpaceBetween", Arrangement.SpaceBetween),
    SpaceAround("SpaceAround", Arrangement.SpaceAround),
}

private fun String?.toHorizontalArrangement() =
    HorizontalArrangementOptions.entries.firstOrNull { it.id == this }?.arrangement
        ?: Arrangement.Start