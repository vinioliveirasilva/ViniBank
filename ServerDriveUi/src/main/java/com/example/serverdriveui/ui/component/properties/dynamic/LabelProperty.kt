package com.example.serverdriveui.ui.component.properties.dynamic

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.static.PropertyBaseData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class LabelProperty(
    private val properties: List<PropertyModel>,
    private val stateManager: ComponentStateManager
) : LabelComponentProperty, PropertyBaseData(properties, "label") {

    private val parsedValue = propertyValue.orEmpty()

    private lateinit var stateFlow: MutableStateFlow<String>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<String>(parsedValue) },
            notNull = { stateManager.registerState<String>(it, parsedValue) }
        )
    }

    override fun getLabel(): StateFlow<String> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<String>(it) ?: stateFlow }
        )
    }

    override fun setLabel(value: String) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState<String>(it, value) }
        )
    }
}