package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString

data class TextProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : TextComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "text",
        transformToData = { it?.asString() },
        defaultPropertyValue = "",
    ) {

    @Composable
    override fun getText() = getValue()
    override fun setText(text: String) = setValue(text)
}

interface TextComponentProperty {

    @Composable
    fun getText() : String
    fun setText(text: String)
}