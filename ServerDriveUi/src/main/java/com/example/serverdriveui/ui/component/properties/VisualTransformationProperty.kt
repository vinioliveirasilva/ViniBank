package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.text.input.VisualTransformation
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.common.runWhen
import com.vini.designsystem.compose.textfield.MaskVisualTransformation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class VisualTransformationProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : VisualTransformationComponentProperty, BasePropertyData(properties, "textFormatter") {
    private val parsedValue = propertyValue.toVisualTransformation()
    private lateinit var stateFlow: MutableStateFlow<VisualTransformation>

    init {
        propertyId.runWhen(
            isNull = { stateFlow = MutableStateFlow<VisualTransformation>(parsedValue) },
            notNull = { stateManager.registerState<VisualTransformation>(it, parsedValue) }
        )
    }

    override fun getVisualTransformation(): StateFlow<VisualTransformation> {
        return propertyId.runWhen(
            isNull = { stateFlow },
            notNull = { stateManager.getState<VisualTransformation>(it) ?: stateFlow }
        )
    }

    override fun setVisualTransformation(visualTransformation: VisualTransformation) {
        propertyId.runWhen(
            isNull = { stateFlow.update { visualTransformation } },
            notNull = { stateManager.updateState<VisualTransformation>(it, visualTransformation) }
        )
    }


    private fun String?.toVisualTransformation() = when (this) {
        "Documento.CPF" -> MaskVisualTransformation(
            mask = "###.###.###-##",
            toIgnore = '#'
        )
        "Telefone" -> MaskVisualTransformation(
            mask = "## #####-####",
            toIgnore = '#'
        )
        else -> VisualTransformation.None
    }
}

