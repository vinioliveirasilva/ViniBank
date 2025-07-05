package com.example.serverdriveui

import com.example.serverdriveui.service.SdUiService
import com.example.serverdriveui.service.model.SdUiRequest
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

private data class ScreenModel(
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
)

class SdUiRepository(
    private val sdUiService: SdUiService,
) {
    private val cache = mutableMapOf<String, String>()

    fun getScreen(
        model: SdUiModel
    ): Flow<String> {
        val hasCache = cache.containsKey(model.flowId.plus(model.screenId))
        if (hasCache) {
            return cache[model.flowId.plus(model.screenId)]?.let { flowOf(it) }
                ?: sdUiService.getScreenModel(
                    request = model.toSdUiRequest()
                ).abc(model)
        }

        return sdUiService.getScreenModel(
            request = model.toSdUiRequest()
        ).abc(model)
    }

    private fun Flow<String>.abc(model: SdUiModel) = map {
        val screenModel = Gson().fromJson<ScreenModel>(it, ScreenModel::class.java)

        if(screenModel.shouldCache) {
            cache[model.flowId.plus(model.screenId)] = it
        }
        it
    }

    private fun SdUiModel.toSdUiRequest() = SdUiRequest(
        currentFlow = flowId,
        currentStage = lastScreenId,
        nextStage = screenId,
        flowData = screenData,
    )
}