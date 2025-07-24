package com.example.serverdriveui.service.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class PropertyModel(
    @SerialName("name")
    val name: String,
    @SerialName("value")
    val value: JsonElement,
    @SerialName("id")
    val id: String = "",
) {
    override fun toString(): String {
        return """{"name": $name , "value": "$value", "id": "$id"}"""
    }
}