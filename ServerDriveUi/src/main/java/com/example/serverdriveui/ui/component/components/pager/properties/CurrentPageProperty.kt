package com.example.serverdriveui.ui.component.components.pager.properties

import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager

class CurrentPageProperty(
    private val properties: Map<String, PropertyModel>,
    private val stateManager: ComponentStateManager,
) : CurrentPageComponentProperty,
    BasePropertyData<Int>(
        stateManager = stateManager,
        properties = properties,
        propertyName = "currentPage",
        propertyValueTransformation = { it.toInt() },
        defaultPropertyValue = 0
    ) {
    override fun getCurrentPage() = getValue()

    override fun setCurrentPage(value: Int) = setValue(value)
}