package com.example.serverdriveui.ui.component.properties

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asString
import com.vini.designsystemsdui.property.options.AlignmentOptions

interface ContentAlignmentComponentProperty {

    @Composable
    fun getContentAlignment(): Alignment
    fun setContentAlignment(value: AlignmentOptions)
}

class ContentAlignmentProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,

    ) : ContentAlignmentComponentProperty,
    BasePropertyData<String>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "contentAlignment",
        transformToData = { it?.asString() },
        defaultPropertyValue = AlignmentOptions.TopStart.name,
    ) {

    @Composable
    override fun getContentAlignment() = AlignmentOptions.valueOf(getValue()).toAlignment()

    override fun setContentAlignment(value: AlignmentOptions) = setValue(value.name)

    private fun AlignmentOptions.toAlignment() = when(this) {
        AlignmentOptions.TopStart -> Alignment.TopStart
        AlignmentOptions.TopCenter -> Alignment.TopCenter
        AlignmentOptions.TopEnd -> Alignment.TopEnd
        AlignmentOptions.CenterStart -> Alignment.CenterStart
        AlignmentOptions.Center -> Alignment.Center
        AlignmentOptions.CenterEnd -> Alignment.CenterEnd
        AlignmentOptions.BottomStart -> Alignment.BottomStart
        AlignmentOptions.BottomCenter -> Alignment.BottomCenter
        AlignmentOptions.BottomEnd -> Alignment.BottomEnd
    }
}

