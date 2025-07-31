package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystem.compose.textfield.MaskVisualTransformation
import com.vini.designsystemsdui.PropertyOptions.VisualTransformationOption

class VisualTransformationProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : VisualTransformationComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "visualTransformation",
        transformToData = { it?.asString() },
        defaultPropertyValue = VisualTransformationOption.None.id,
    ) {

    @Composable
    override fun getVisualTransformation() = getValue().toOption().visualTransformation

    override fun setVisualTransformation(visualTransformation: VisualTransformationOption) =
        setValue(visualTransformation.id)
}

private fun String?.toOption() =
    VisualTransformationOption.entries.firstOrNull { it.id == this }
        ?: VisualTransformationOption.None

interface VisualTransformationComponentProperty {
    @Composable
    fun getVisualTransformation(): VisualTransformation
    fun setVisualTransformation(visualTransformation: VisualTransformationOption)
}