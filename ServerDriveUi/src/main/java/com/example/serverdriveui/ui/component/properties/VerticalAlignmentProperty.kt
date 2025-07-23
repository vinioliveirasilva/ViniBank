package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

class VerticalAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : VerticalAlignmentComponentProperty,
    BasePropertyData<Alignment.Vertical>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalAlignment",
        propertyValueTransformation = { it.toOption() },
        defaultPropertyValue = VerticalAlignmentOption.Top.id,
        scope = scope
    ) {
    override fun getVerticalAlignment() = getValue()
    override fun setVerticalAlignment(value: VerticalAlignmentOption) = setValue(value.id)
}

enum class VerticalAlignmentOption(val id: String, val verticalAlignment: Alignment.Vertical) {
    Top("Top", Alignment.Top),
    Center("Center", Alignment.CenterVertically),
    Bottom("Bottom", Alignment.Bottom),
}

private fun String?.toOption() =
    VerticalAlignmentOption.entries.firstOrNull { it.id == this }?.verticalAlignment
        ?: VerticalAlignmentOption.Top.verticalAlignment