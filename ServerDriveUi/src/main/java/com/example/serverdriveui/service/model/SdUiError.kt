package com.example.serverdriveui.service.model

data class SdUiError(
    val message: String,
    val code: Int,
    val screen: ScreenModel,
)