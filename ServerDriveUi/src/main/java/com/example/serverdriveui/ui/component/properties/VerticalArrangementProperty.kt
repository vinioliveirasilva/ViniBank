package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

class VerticalArrangementProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : VerticalArrangementComponentProperty,
    BasePropertyData<Arrangement.Vertical>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalArrangement",
        propertyValueTransformation = { it.toVerticalArrangement() },
        defaultPropertyValue = VerticalArrangementOption.Top.id,
        scope = scope
    ) {
    override fun getVerticalArrangement() = getValue()
    override fun setVerticalArrangement(value: VerticalArrangementOption) = setValue(value.id)
}

enum class VerticalArrangementOption(val id: String, val verticalArrangement: Arrangement.Vertical) {
    Top("Top", Arrangement.Top),
    Bottom("Bottom", Arrangement.Bottom),
    Center("Center", Arrangement.Center),
    SpaceAround("SpaceAround", Arrangement.SpaceAround),
    SpaceBetween("SpaceBetween", Arrangement.SpaceBetween),
    SpaceEvenly("SpaceEvenly", Arrangement.SpaceEvenly),
}

private fun String?.toVerticalArrangement() =
    VerticalArrangementOption.entries.firstOrNull { it.id == this }?.verticalArrangement ?: when (true) {
        this?.contains("SpacedBy") -> parseSpacedBy()
        else -> Arrangement.Top
    }

private fun String.parseSpacedBy() = (split("SpacedBy").lastOrNull()?.toInt() ?: 0).let {
    Arrangement.spacedBy(space = it.dp)
}