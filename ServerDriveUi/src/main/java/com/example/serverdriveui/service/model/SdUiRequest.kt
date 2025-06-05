package com.example.serverdriveui.service.model

import com.google.gson.annotations.SerializedName

data class SdUiRequest(
    @SerializedName("screenId")
    val screenId: String,
    @SerializedName("screenData")
    val screenData: String,
)