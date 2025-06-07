package com.example.serverdriveui

import com.example.serverdriveui.service.SdUiService
import com.example.serverdriveui.service.model.SdUiRequest
import com.example.serverdriveui.ui.component.manager.ComponentParser

class SdUiRepository(
    private val sdUiService: SdUiService,
    private val componentParser: ComponentParser,
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