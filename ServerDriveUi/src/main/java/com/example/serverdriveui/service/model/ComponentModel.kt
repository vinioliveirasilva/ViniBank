package com.example.serverdriveui.service.model


import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ComponentModel(
    @SerializedName("staticProperty")
    val staticProperty: Map<String, String> = mapOf(),
    @SerializedName("dynamicProperty")
    val dynamicProperty: List<PropertyModel> = emptyList(),
    @SerializedName("type")
    val type: String,
    @SerializedName("components")
    val components: List<ComponentModel> = emptyList(),
    @SerializedName("action")
    val action: ActionModel? = null,
    @SerializedName("validator")
    val validators: List<ValidatorModel> = emptyList()
)