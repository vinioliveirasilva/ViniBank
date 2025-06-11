package com.example.serverdriveui.ui.component.properties.dynamic

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.static.PropertyBaseData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.asValue
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class VerticalPaddingProperty(
    private val properties: List<PropertyModel>,
    private val stateManager: ComponentStateManager
) : PropertyBaseData(properties, "paddingVertical"), VerticalPaddingComponentProperty {

    private val parsedValue = propertyValue?.toIntOrNull() ?: 0
    private lateinit var stateFlow: MutableStateFlow<Int>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<Int>(parsedValue) },
            notNull = { stateManager.registerState<Int>(it, parsedValue) }
        )
    }

    override val verticalPaddingModifier: Modifier
        @Composable
        get() = Modifier.padding(vertical = getVerticalPadding().asValue().dp)

    override fun getVerticalPadding(): StateFlow<Int> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<Int>(it) ?: stateFlow }
        )
    }

    override fun setVerticalPadding(padding: Int) {
        propertyId.runWhen(
            isNull = { stateFlow.update { padding } },
            notNull = { stateManager.updateState<Int>(it, padding) }
        )
    }
}