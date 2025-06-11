package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.dynamic.VerticalFillType.Companion.toVerticalFillType
import com.example.serverdriveui.ui.component.properties.static.PropertyBaseData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class VerticalFillTypeProperty(
    private val properties: List<PropertyModel>,
    private val stateManager: ComponentStateManager
) : VerticalFillTypeComponentProperty, PropertyBaseData(properties, "verticalFillType") {

    private val parsedValue = propertyValue.toVerticalFillType()
    private lateinit var stateFlow: MutableStateFlow<VerticalFillType>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<VerticalFillType>(parsedValue) },
            notNull = { stateManager.registerState<VerticalFillType>(it, parsedValue) }
        )
    }

    override val verticalFillTypeModifier: Modifier
        get() = parsedValue.modifier

    override fun getVerticalFillType(): StateFlow<VerticalFillType> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<VerticalFillType>(it) ?: stateFlow }
        )
    }

    override fun setVerticalFillType(value: VerticalFillType) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState<VerticalFillType>(it, value) }
        )
    }
}