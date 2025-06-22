package com.example.serverdriveui.ui.component.properties

import com.example.serverdriveui.service.model.PropertyModel

open class BasePropertyData(
    private val properties: Map<String, PropertyModel>,
    private val propertyName: String,
) {
    private val propertyData = properties[propertyName]
    val propertyId = propertyData?.id.orEmpty()
    val propertyValue = propertyData?.value
}