package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.HorizontalAlignmentOptions

class HorizontalAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : HorizontalAlignmentComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalAlignment",
        transformToData = { it?.asString() },
        defaultPropertyValue = HorizontalAlignmentOptions.Start.name,
    ) {

    @Composable
    override fun getHorizontalAlignment() = HorizontalAlignmentOptions.valueOf(getValue()).toAlignment()

    override fun setHorizontalAlignment(value: HorizontalAlignmentOptions) = setValue(value.name)

    private fun HorizontalAlignmentOptions.toAlignment() = when(this) {
        HorizontalAlignmentOptions.Center -> Alignment.CenterHorizontally
        HorizontalAlignmentOptions.Start -> Alignment.Start
        HorizontalAlignmentOptions.End -> Alignment.End
    }
}

interface HorizontalAlignmentComponentProperty {
    @Composable
    fun getHorizontalAlignment(): Alignment.Horizontal
    fun setHorizontalAlignment(value: HorizontalAlignmentOptions)
}