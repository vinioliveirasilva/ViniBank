package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class VerticalAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : VerticalAlignmentComponentProperty, BasePropertyData(properties, "verticalAlignment")  {
    private val parsedValue = propertyValue.toVerticalAlignment()
    private lateinit var stateFlow: MutableStateFlow<Alignment.Vertical>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<Alignment.Vertical>(parsedValue) },
            notNull = { stateManager.registerState<Alignment.Vertical>(it, parsedValue) }
        )
    }

    override fun getVerticalAlignment(): StateFlow<Alignment.Vertical> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<Alignment.Vertical>(it) ?: stateFlow }
        )
    }

    override fun setVerticalAlignment(value: Alignment.Vertical) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState<Alignment.Vertical>(it, value) }
        )
    }

    private fun String?.toVerticalAlignment(): Alignment.Vertical = when (this) {
        "Center" -> Alignment.Companion.CenterVertically
        "Bottom" -> Alignment.Companion.Bottom
        "Top" -> Alignment.Companion.Top
        else -> Alignment.Companion.Top
    }
}