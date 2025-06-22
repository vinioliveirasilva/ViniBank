package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.Arrangement
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class HorizontalArrangementProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : HorizontalArrangementComponentProperty, BasePropertyData(properties, "horizontalArrangement") {
    private val parsedValue = propertyValue.toHorizontalArrangement()
    private lateinit var stateFlow: MutableStateFlow<Arrangement.Horizontal>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<Arrangement.Horizontal>(parsedValue) },
            notNull = { stateManager.registerState<Arrangement.Horizontal>(it, parsedValue) }
        )
    }

    override fun getHorizontalArrangement(): StateFlow<Arrangement.Horizontal> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<Arrangement.Horizontal>(it) ?: stateFlow }
        )
    }

    override fun setHorizontalArrangement(value: Arrangement.Horizontal) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState<Arrangement.Horizontal>(it, value) }
        )
    }

    private fun String?.toHorizontalArrangement() = when (this) {
        "Start" -> Arrangement.Start
        "End" -> Arrangement.End
        "Center" -> Arrangement.Center
        "SpaceBetween" -> Arrangement.SpaceBetween
        "SpaceAround" -> Arrangement.SpaceAround
        else -> Arrangement.Start
    }
}