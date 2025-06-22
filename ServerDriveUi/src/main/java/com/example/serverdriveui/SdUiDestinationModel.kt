package com.example.serverdriveui

data class SdUiDestinationModel(
    val flowId: String,
    val screenId: String = "",
    val screenData: String = "{}",
    val lastScreenId: String = "",
)