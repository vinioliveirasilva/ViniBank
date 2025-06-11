package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.foundation.layout.Arrangement
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.static.PropertyBaseData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class VerticalArrangementProperty(
    private val properties: List<PropertyModel>,
    private val stateManager: ComponentStateManager
) : VerticalArrangementComponentProperty, PropertyBaseData(properties, "verticalArrangement")  {
    private val parsedValue = propertyValue.toVerticalArrangement()
    private lateinit var stateFlow: MutableStateFlow<Arrangement.Vertical>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<Arrangement.Vertical>(parsedValue) },
            notNull = { stateManager.registerState<Arrangement.Vertical>(it, parsedValue) }
        )
    }

    override fun getVerticalArrangement(): StateFlow<Arrangement.Vertical> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<Arrangement.Vertical>(it) ?: stateFlow }
        )
    }

    override fun setVerticalArrangement(value: Arrangement.Vertical) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState<Arrangement.Vertical>(it, value) }
        )
    }

    private fun String?.toVerticalArrangement() = when(this) {
        "Top" -> Arrangement.Top
        "Bottom" -> Arrangement.Bottom
        "Center" -> Arrangement.Center
        "SpaceAround" -> Arrangement.SpaceAround
        "SpaceBetween" -> Arrangement.SpaceBetween
        "SpaceEvenly" -> Arrangement.SpaceEvenly
        else -> Arrangement.Top
    }
}