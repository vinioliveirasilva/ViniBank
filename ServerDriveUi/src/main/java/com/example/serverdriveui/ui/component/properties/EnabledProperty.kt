package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class EnabledProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : EnabledComponentProperty, BasePropertyData(properties, "enabled") {
    private val parsedValue = propertyValue?.toBoolean() != false

    private lateinit var stateFlow: MutableStateFlow<Boolean>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<Boolean>(parsedValue) },
            notNull = { stateManager.registerState<Boolean>(it, parsedValue) }
        )
    }

    override fun getEnabled(): StateFlow<Boolean> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<Boolean>(it) ?: stateFlow }
        )
    }

    override fun setEnabled(value: Boolean) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState<Boolean>(it, value) }
        )
    }
}