package com.example.serverdriveui.ui.component.components.pager.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asInt

class ContentPaddingProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,

) : ContentPaddingComponentProperty,
    BasePropertyData<Int>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "contentPadding",
        transformToData = { it?.asInt() },
        defaultPropertyValue = 0,
    ) {

    @Composable
    override fun getContentPadding() = getValue()

    override fun setContentPadding(value: Int) = setValue(value)
}

interface ContentPaddingComponentProperty {

    @Composable
    fun getContentPadding(): Int
    fun setContentPadding(value: Int)
}