package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.text.style.TextAlign
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.CoroutineScope

class TextAlignProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : TextAlignComponentProperty,
    BasePropertyData<TextAlign>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "textAlign",
        propertyValueTransformation = { it.toTextAlign() },
        defaultPropertyValue = TextAlignOption.Start.id,
        scope = scope
    ) {
    override fun getTextAlign() = getValue()
    override fun setTextAlign(textAlign: TextAlignOption) = setValue(textAlign.id)
}

enum class TextAlignOption(val id: String, val textAlign: TextAlign) {
    Start("Start", TextAlign.Start),
    Center("Center", TextAlign.Center),
    End("End", TextAlign.End),
}

private fun String?.toTextAlign(): TextAlign = TextAlignOption.entries.firstOrNull { it.id == this }?.textAlign ?: TextAlignOption.Start.textAlign