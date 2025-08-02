package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.options.HorizontalAlignmentOption

class HorizontalAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : HorizontalAlignmentComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalAlignment",
        transformToData = { it?.asString() },
        defaultPropertyValue = HorizontalAlignmentOption.Start.name,
    ) {

    @Composable
    override fun getHorizontalAlignment() = HorizontalAlignmentOption.valueOf(getValue()).toAlignment()

    override fun setHorizontalAlignment(value: HorizontalAlignmentOption) = setValue(value.name)

    private fun HorizontalAlignmentOption.toAlignment() = when(this) {
        HorizontalAlignmentOption.Center -> Alignment.CenterHorizontally
        HorizontalAlignmentOption.Start -> Alignment.Start
        HorizontalAlignmentOption.End -> Alignment.End
    }
}

interface HorizontalAlignmentComponentProperty {
    @Composable
    fun getHorizontalAlignment(): Alignment.Horizontal
    fun setHorizontalAlignment(value: HorizontalAlignmentOption)
}