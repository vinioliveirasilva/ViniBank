package com.example.serverdriveui.ui.component.properties.dynamic

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class EnabledProperty(
    private val properties: List<PropertyModel>,
    private val stateManager: ComponentStateManager
) : EnabledComponentProperty {
    private val propertyData = properties.find { it.name == "enabled" }
    private val propertyId = propertyData?.id.orEmpty()
    private val propertyValue = propertyData?.value?.toBoolean() != false

    private lateinit var stateFlow: MutableStateFlow<Boolean>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<Boolean>(propertyValue) },
            notNull = { stateManager.registerState<Boolean>(it, propertyValue) }
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