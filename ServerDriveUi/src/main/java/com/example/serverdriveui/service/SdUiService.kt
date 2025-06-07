package com.example.serverdriveui.service

import com.example.network.Encrypt
import com.example.serverdriveui.service.model.ScreenModel
import com.example.serverdriveui.service.model.SdUiRequest
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.POST

interface SdUiService {
    @Encrypt
    @POST("/sdui_screens")
    fun getScreenModel(@Body request: SdUiRequest): Flow<ScreenModel>
}