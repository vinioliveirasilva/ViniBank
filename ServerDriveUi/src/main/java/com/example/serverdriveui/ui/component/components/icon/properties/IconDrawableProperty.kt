package com.example.serverdriveui.ui.component.components.icon.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystem.R

class IconDrawableProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : IconDrawableComponent,
    BasePropertyData<String?>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "iconDrawable",
        transformToData = { it?.asString() },
        defaultPropertyValue = null,
    ) {

    override val drawableIcon: Int?
        @Composable
        get() = IconLibrary[getValue()]
}

private val IconLibrary: Map<String, Int>
    get() = mapOf(
        "Pix" to R.drawable.pix,
        "Visa" to R.drawable.visa,
        "Mastercard" to R.drawable.mastercard,
    )

interface IconDrawableComponent {
    @get:Composable
    val drawableIcon: Int?
}