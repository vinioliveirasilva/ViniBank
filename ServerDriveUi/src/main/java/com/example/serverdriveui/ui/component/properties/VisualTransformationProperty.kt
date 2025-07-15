package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.text.input.VisualTransformation
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.designsystem.compose.textfield.MaskVisualTransformation

class VisualTransformationProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : VisualTransformationComponentProperty,
    BasePropertyData<VisualTransformation>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "textFormatter",
        propertyValueTransformation = { it.toVisualTransformation() },
        defaultPropertyValue = VisualTransformation.None
    ) {
    override fun getVisualTransformation() = getValue()
    override fun setVisualTransformation(visualTransformation: VisualTransformation) =
        setValue(visualTransformation)
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

