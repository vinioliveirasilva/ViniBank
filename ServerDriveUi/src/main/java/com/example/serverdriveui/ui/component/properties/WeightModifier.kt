package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class WeightModifier(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : WeightComponentModifier, BasePropertyData(properties, "weight") {
    private val propertyParsed = propertyValue?.toFloat()

    private lateinit var stateFlow: MutableStateFlow<Float?>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<Float?>(propertyParsed) },
            notNull = { stateManager.registerState<Float?>(it, propertyParsed) }
        )
    }

    override fun getWeight(): StateFlow<Float?> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<Float?>(it) ?: stateFlow }
        )
    }

    override fun setWeight(value: Float) {
        propertyId.runWhen(
            isNull = { stateFlow.update { value } },
            notNull = { stateManager.updateState<Float>(it, value) }
        )
    }

    override val ColumnScope.weightModifier: Modifier
        @Composable
        get() =   getWeight().asValue()?.let { Modifier.weight(it) } ?: Modifier
}