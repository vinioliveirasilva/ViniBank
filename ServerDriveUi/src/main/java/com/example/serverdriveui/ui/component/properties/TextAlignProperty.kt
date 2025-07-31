package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.TextAlignOption

class TextAlignProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : TextAlignComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "textAlign",
        transformToData = { it?.asString() },
        defaultPropertyValue = TextAlignOption.Start.name,
    ) {

    @Composable
    override fun getTextAlign() = TextAlignOption.valueOf(getValue()).textAlign

    override fun setTextAlign(textAlign: TextAlignOption) = setValue(textAlign.name)
}

interface TextAlignComponentProperty {

    @Composable
    fun getTextAlign(): TextAlign
    fun setTextAlign(textAlign: TextAlignOption)
}