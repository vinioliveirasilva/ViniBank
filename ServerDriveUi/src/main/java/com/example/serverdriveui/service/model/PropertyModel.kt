package com.example.serverdriveui.service.model


import com.google.gson.annotations.SerializedName

data class PropertyModel(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String,
    @SerializedName("id")
    val id: String = "",
) {
    override fun toString(): String {
        return """{"name": $name , "value": "$value", "id": "$id"}"""
    }
}