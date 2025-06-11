package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.ui.text.style.TextAlign
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class TextAlignProperty(
    private val properties: List<PropertyModel>,
    private val stateManager: ComponentStateManager
) : TextAlignComponentProperty {
    private val propertyData = properties.find { it.name == "textAlign" }
    private val propertyId = propertyData?.id.orEmpty()
    private val propertyValue = propertyData?.value.toTextAlign()

    private lateinit var stateFlow: MutableStateFlow<TextAlign>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<TextAlign>(propertyValue) },
            notNull = { stateManager.registerState<TextAlign>(it, propertyValue) }
        )
    }

    override fun getTextAlign(): StateFlow<TextAlign> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<TextAlign>(it) ?: stateFlow }
        )
    }

    override fun setTextAlign(textAlign: TextAlign) {
        propertyId.runWhen(
            isNull = { stateFlow.update { textAlign } },
            notNull = { stateManager.updateState<TextAlign>(it, textAlign) }
        )
    }

    private fun String?.toTextAlign(): TextAlign = when (this) {
        "Center" -> TextAlign.Center
        "Start" -> TextAlign.Start
        "End" -> TextAlign.End
        else -> TextAlign.Start
    }
}