package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asBoolean

class VisibilityProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : VisibilityComponentProperty,
    BasePropertyData<Boolean>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "isVisible",
        defaultPropertyValue = true,
        transformToData = { it?.asBoolean() }
    ) {

    @Composable
    override fun getIsVisible() = getValue()
    override fun setIsVisible(isVisible: Boolean) = setValue(isVisible)
}

interface VisibilityComponentProperty {
    @Composable
    fun getIsVisible(): Boolean
    fun setIsVisible(isVisible: Boolean)
}