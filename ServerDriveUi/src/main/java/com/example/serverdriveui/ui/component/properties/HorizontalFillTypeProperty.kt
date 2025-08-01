package com.example.serverdriveui.ui.component.properties

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.options.HorizontalFillTypeOption

data class HorizontalFillTypeProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : HorizontalFillTypeComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "horizontalFillType",
        defaultPropertyValue = HorizontalFillTypeOption.None.name,
        transformToData = { it?.asString() }
    ) {

    override val horizontalFillTypeModifier: Modifier
        @Composable
        get() = HorizontalFillTypeOption.valueOf(getValue()).toFillType()

    @SuppressLint("ModifierFactoryExtensionFunction")
    private fun HorizontalFillTypeOption.toFillType() = when (this) {
        HorizontalFillTypeOption.Max -> Modifier.fillMaxWidth()
        HorizontalFillTypeOption.Half -> Modifier.fillMaxWidth(.5f)
        HorizontalFillTypeOption.Quarter -> Modifier.fillMaxWidth(.25f)
        HorizontalFillTypeOption.Wrap -> Modifier.wrapContentWidth()
        HorizontalFillTypeOption.None -> Modifier

    }
}

interface HorizontalFillTypeComponentProperty {
    @get:Composable
    val horizontalFillTypeModifier: Modifier
}