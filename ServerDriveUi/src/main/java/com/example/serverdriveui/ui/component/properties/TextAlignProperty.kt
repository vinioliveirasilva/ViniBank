package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.text.style.TextAlign
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager

class TextAlignProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : TextAlignComponentProperty,
    BasePropertyData<TextAlign>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "textAlign",
        propertyValueTransformation = { it.toTextAlign() },
        defaultPropertyValue = TextAlign.Start
    ) {
    override fun getTextAlign() = getValue()
    override fun setTextAlign(textAlign: TextAlign) = setValue(textAlign)
}

private fun String?.toTextAlign(): TextAlign = when (this) {
    "Center" -> TextAlign.Center
    "Start" -> TextAlign.Start
    "End" -> TextAlign.End
    else -> TextAlign.Start
}