package com.example.serverdriveui

import com.example.serverdriveui.service.SdUiService
import com.example.serverdriveui.service.model.SdUiRequest

class SdUiRepository(
    private val sdUiService: SdUiService,
) {
    fun getScreen(
        model: SdUiModel
    ) = sdUiService
        .getScreenModel(model.toSdUiRequest())

    private fun SdUiModel.toSdUiRequest() = SdUiRequest(
        currentFlow = flowId,
        currentStage = lastScreenId,
        nextStage = screenId,
        flowData = screenData,
    )
}