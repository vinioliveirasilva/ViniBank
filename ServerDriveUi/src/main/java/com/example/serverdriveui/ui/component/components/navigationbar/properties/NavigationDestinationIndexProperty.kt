package com.example.serverdriveui.ui.component.components.navigationbar.properties

import androidx.compose.runtime.Composable
import com.example.serverdriveui.service.model.PropertyModel
import com.example.serverdriveui.ui.component.properties.BasePropertyData
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.util.JsonUtil.asInt

class NavigationDestinationIndexProperty(
    properties: Map<String, PropertyModel>,
    stateManager: ComponentStateManager,
) : NavigationDestinationIndexComponent, BasePropertyData<Int>(
    stateManager = stateManager,
    properties = properties,
    propertyName = "index",
    transformToData = { it?.asInt() },
    defaultPropertyValue = 0,
) {

    @Composable
    override fun getIndex() = getValue()
}

interface NavigationDestinationIndexComponent {
    @Composable
    fun getIndex(): Int
}