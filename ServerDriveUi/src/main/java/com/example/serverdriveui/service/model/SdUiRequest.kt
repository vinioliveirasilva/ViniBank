package com.example.serverdriveui.service.model

import com.google.gson.annotations.SerializedName

data class SdUiRequest(
    @SerializedName("flow")
    val flow: String,
    @SerializedName("fromScreen")
    val fromScreen: String,
    @SerializedName("toScreen")
    val toScreen: String,
    @SerializedName("screenData")
    val screenData: String,
)