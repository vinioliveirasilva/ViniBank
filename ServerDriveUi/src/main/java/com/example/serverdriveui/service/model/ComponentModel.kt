package com.example.serverdriveui.service.model


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ComponentModel(
    @SerializedName("type")
    val type: String,
    @SerializedName("properties")
    val properties: List<PropertyModel> = emptyList(),
    @SerializedName("components")
    val components: List<ComponentModel> = emptyList(),
    @SerializedName("action")
    val actions: ActionModel? = null,
    @SerializedName("validator")
    val validators: List<ValidatorModel> = emptyList()
)