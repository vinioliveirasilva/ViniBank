package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

class HorizontalAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : HorizontalAlignmentComponentProperty,
    BasePropertyData<Alignment.Horizontal>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalAlignment",
        propertyValueTransformation = { it.toHorizontalAlignment() },
        defaultPropertyValue = HorizontalAlignmentOptions.Start.id,
        scope = scope
    ) {
    override fun getHorizontalAlignment() = getValue()
    override fun setHorizontalAlignment(value: HorizontalAlignmentOptions) = setValue(value.id)
}

enum class HorizontalAlignmentOptions(val id: String, val alignment: Alignment.Horizontal) {
    Center("Center", Alignment.CenterHorizontally),
    Start("Start", Alignment.Start),
    End("End", Alignment.End),
}

private fun String?.toHorizontalAlignment(): Alignment.Horizontal =
    HorizontalAlignmentOptions.entries.firstOrNull { it.id == this }?.alignment ?: Alignment.Start