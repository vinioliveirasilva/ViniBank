package com.example.serverdriveui.ui.component.properties.static

import com.example.serverdriveui.service.model.PropertyModel

open class PropertyBaseData(
    properties: List<PropertyModel>,
    private val propertyName: String,
) {
    private val propertyData = properties.find { it.name == propertyName }
    val propertyId = propertyData?.id.orEmpty()
    val propertyValue = propertyData?.value
}