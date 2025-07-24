package com.example.serverdriveui.ui.component.components.pager.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asInt

class CurrentPageProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,

) : CurrentPageComponentProperty,
    BasePropertyData<Int>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "currentPage",
        transformToData = { it?.asInt() },
        defaultPropertyValue = 0,
    ) {

    @Composable
    override fun getCurrentPage() = getValue()

    override fun setCurrentPage(value: Int) = setValue(value)
}