package com.example.serverdriveui.ui.component.properties

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.or
import com.vini.common.runWhen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SizeModifier(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager
) : SizeComponentModifier, BasePropertyData(properties, "size") {

    override val sizeModifier: Modifier
        @Composable
        get() = getSize().collectAsState().value?.let { Modifier.size(it.dp) } or Modifier

    private val parsedValue = propertyValue?.toIntOrNull()
    private lateinit var stateFlow: MutableStateFlow<Int?>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<Int?>(parsedValue) },
            notNull = { stateManager.registerState<Int?>(it, parsedValue) }
        )
    }

    override fun getSize(): StateFlow<Int?> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<Int?>(it) ?: stateFlow }
        )
    }

    override fun setSize(size: Int) {
        propertyId.runWhen(
            isNull = { stateFlow.update { size } },
            notNull = { stateManager.updateState<Int>(it, size) }
        )
    }
}