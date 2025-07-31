package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.PropertyOptions.HorizontalAlignmentOptions

class HorizontalAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : HorizontalAlignmentComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalAlignment",
        transformToData = { it?.asString() },
        defaultPropertyValue = HorizontalAlignmentOptions.Start.id,
    ) {

    @Composable
    override fun getHorizontalAlignment() = getValue().toOption().alignment

    override fun setHorizontalAlignment(value: HorizontalAlignmentOptions) = setValue(value.id)
}

private fun String?.toOption() =
    HorizontalAlignmentOptions.entries.firstOrNull { it.id == this }
        ?: HorizontalAlignmentOptions.Start

interface HorizontalAlignmentComponentProperty {
    @Composable
    fun getHorizontalAlignment(): Alignment.Horizontal
    fun setHorizontalAlignment(value: HorizontalAlignmentOptions)
}