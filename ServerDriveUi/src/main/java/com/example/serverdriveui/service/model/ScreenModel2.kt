package com.example.serverdriveui.service.model


import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ScreenModel2(
    @SerializedName("shouldCache")
    val shouldCache: Boolean = true,
    @SerializedName("flow")
    val flow: String,
    @SerializedName("stage")
    val stage: String,
    @SerializedName("template")
    val template: String,
    @SerializedName("version")
    val version: String,
    @SerializedName("components")
    val components: Map<String, String>, //{ "button" : {} }
)

@Serializable
data class ComponentModel2(
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
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}

