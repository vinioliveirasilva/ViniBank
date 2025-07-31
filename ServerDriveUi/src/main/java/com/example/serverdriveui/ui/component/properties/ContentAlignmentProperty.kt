package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.AlignmentOptions

interface ContentAlignmentComponentProperty {

    @Composable
    fun getContentAlignment(): Alignment
    fun setContentAlignment(value: AlignmentOptions)
}

class ContentAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,

    ) : ContentAlignmentComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "contentAlignment",
        transformToData = { it?.asString() },
        defaultPropertyValue = AlignmentOptions.TopStart.name,
    ) {

    @Composable
    override fun getContentAlignment() = AlignmentOptions.valueOf(getValue()).alignment

    override fun setContentAlignment(value: AlignmentOptions) = setValue(value.name)
}

