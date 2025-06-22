package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HorizontalAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : HorizontalAlignmentComponentProperty, BasePropertyData(properties, "horizontalAlignment")  {
    private val parsedValue = propertyValue.toHorizontalAlignment()
    private lateinit var stateFlow: MutableStateFlow<Alignment.Horizontal>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<Alignment.Horizontal>(parsedValue) },
            notNull = { stateManager.registerState<Alignment.Horizontal>(it, parsedValue) }
        )
    }

    override fun getHorizontalAlignment(): StateFlow<Alignment.Horizontal> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<Alignment.Horizontal>(it) ?: stateFlow }
        )
    }

    override fun setHorizontalAlignment(value: Alignment.Horizontal) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState<Alignment.Horizontal>(it, value) }
        )
    }

    private fun String?.toHorizontalAlignment() : Alignment.Horizontal = when(this) {
        "Center" -> Alignment.CenterHorizontally
        "Start" -> Alignment.Start
        "End" -> Alignment.End
        else -> Alignment.Start
    }
}