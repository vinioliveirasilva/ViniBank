package com.example.serverdriveui.service.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PropertyModel(
    @SerialName("name")
    val name: String,
    @SerialName("value")
    val value: String,
    @SerialName("id")
    val id: String = "",
) {
    override fun toString(): String {
        return """{"name": $name , "value": "$value", "id": "$id"}"""
    }
}