package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asFloat

interface FontSizeComponentProperty {
    @Composable
    fun getFontSize(): Float
    fun setFontSize(size: Float)
}

data class FontSizeProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : FontSizeComponentProperty, BasePropertyData<Float>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "fontSize",
    transformToData = { it?.asFloat() },
    defaultPropertyValue = 14f
) {
    @Composable
    override fun getFontSize() = getValue()
    override fun setFontSize(size: Float) = setValue(size)
}