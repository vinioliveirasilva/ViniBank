package com.example.serverdriveui.ui.components.properties.dynamic

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
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
        runOnPropertyId(
            isNull = { stateFlow = MutableStateFlow<Boolean>(propertyValue) },
            notNull = { stateManager.registerState<Boolean>(it, propertyValue) }
        )
    }

    override fun getEnabled(): StateFlow<Boolean> {
        return runOnPropertyId(
            isNull = { stateFlow },
            notNull = { stateManager.getState<Boolean>(it) ?: stateFlow }
        )
    }

    override fun setEnabled(value: Boolean) {
        runOnPropertyId(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState<Boolean>(it, value) }
        )
    }

    private fun <T> runOnPropertyId(isNull: () -> T, notNull: (String) -> T) : T {
        return if(propertyId.isBlank()) {
            isNull()
        } else {
            notNull(propertyId)
        }
    }
}