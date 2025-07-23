package com.example.serverdriveui.ui.component.properties

import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.VisualTransformationOption.None
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.vini.designsystem.compose.textfield.MaskVisualTransformation
import kotlinx.coroutines.CoroutineScope

class VisualTransformationProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
    private val scope: CoroutineScope,
) : VisualTransformationComponentProperty,
    BasePropertyData<VisualTransformation>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "visualTransformation",
        propertyValueTransformation = { it.toOption() },
        defaultPropertyValue = None.id,
        scope = scope
    ) {
    override fun getVisualTransformation() = getValue()
    override fun setVisualTransformation(visualTransformation: VisualTransformationOption) =
        setValue(visualTransformation.id)
}

private fun String?.toOption() =
    VisualTransformationOption.entries.firstOrNull { it.id == this }?.visualTransformation
        ?: VisualTransformation.None

enum class VisualTransformationOption(
    val id: String,
    val visualTransformation: VisualTransformation,
) {
    None("None", VisualTransformation.None),
    Password("Password", PasswordVisualTransformation()),
    Phone(
        "Phone",
        MaskVisualTransformation(
            mask = "## #####-####",
            toIgnore = '#'
        )
    ),
    Document(
        "Documento.CPF",
        MaskVisualTransformation(
            mask = "###.###.###-##",
            toIgnore = '#'
        )
    ),
}