package com.example.serverdriveui.service.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class ValidatorModel(
    @SerialName("type") val type: String,
    @SerialName("data") val data: JsonObject,
    @SerialName("id") val id: String,
    @SerialName("required") val required: List<String> = emptyList(),
)