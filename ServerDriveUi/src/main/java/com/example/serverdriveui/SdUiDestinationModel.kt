package com.example.serverdriveui

import kotlinx.serialization.json.JsonObject

data class SdUiDestinationModel(
    val flowId: String,
    val screenId: String = "",
    val screenData: JsonObject = JsonObject(emptyMap()),
    val lastScreenId: String = "",
)