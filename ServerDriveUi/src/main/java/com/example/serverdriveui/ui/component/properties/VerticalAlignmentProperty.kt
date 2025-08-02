package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.options.VerticalAlignmentOption

class VerticalAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : VerticalAlignmentComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "verticalAlignment",
        transformToData = { it?.asString() },
        defaultPropertyValue = VerticalAlignmentOption.Top.name,
    ) {

    @Composable
    override fun getVerticalAlignment() = VerticalAlignmentOption.valueOf(getValue()).toAlignment()

    override fun setVerticalAlignment(value: VerticalAlignmentOption) = setValue(value.name)

    private fun VerticalAlignmentOption.toAlignment() = when(this) {
        VerticalAlignmentOption.Top -> Alignment.Companion.Top
        VerticalAlignmentOption.Center -> Alignment.Companion.CenterVertically
        VerticalAlignmentOption.Bottom -> Alignment.Companion.Bottom
    }
}

interface VerticalAlignmentComponentProperty {
    @Composable
    fun getVerticalAlignment(): Alignment.Vertical
    fun setVerticalAlignment(value: VerticalAlignmentOption)
}