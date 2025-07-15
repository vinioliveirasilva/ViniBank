package com.example.serverdriveui.service.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ValidatorModel(
    @SerializedName("type") val type : String,
    @SerializedName("data") val data : Map<String, String> = emptyMap(),//TODO deve ser somente string/string? impossibilita definir como object no backend
    @SerializedName("id") val id : String,
    @SerializedName("required") val required : List<String>,
)