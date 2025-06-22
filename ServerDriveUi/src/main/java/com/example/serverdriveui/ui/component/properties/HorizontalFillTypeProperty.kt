package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.HorizontalFillType.Companion.toHorizontalFillType
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class HorizontalFillTypeProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : HorizontalFillTypeComponentProperty, BasePropertyData(properties, "horizontalFillType") {

    private val parsedValue = propertyValue.toHorizontalFillType()
    private lateinit var stateFlow: MutableStateFlow<HorizontalFillType>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<HorizontalFillType>(parsedValue) },
            notNull = { stateManager.registerState<HorizontalFillType>(it, parsedValue) }
        )
    }

    override val horizontalFillTypeModifier: Modifier
        get() = parsedValue.modifier

    override fun getHorizontalFillType(): StateFlow<HorizontalFillType> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<HorizontalFillType>(it) ?: stateFlow }
        )
    }

    override fun setHorizontalFillType(value: HorizontalFillType) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState<HorizontalFillType>(it, value) }
        )
    }
}