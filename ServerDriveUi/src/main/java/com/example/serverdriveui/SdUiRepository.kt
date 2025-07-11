package com.example.serverdriveui

import com.example.serverdriveui.service.SdUiService
import com.example.serverdriveui.service.model.SdUiRequest
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.delay
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
        val identifier = with(model) { "$flow/$toScreen" }
        val hasCache = cache.containsKey(identifier)
        if (hasCache) {
            return cache[identifier]?.let { flowOf(it) }
                ?: sdUiService.getScreenModel(
                    request = model.toSdUiRequest()
                ).abc()
        }

        return sdUiService.getScreenModel(
            request = model.toSdUiRequest()
        ).abc()
    }

    private fun Flow<String>.abc() = map {
        delay(1000)
        val model = Gson().fromJson(it, ScreenModel::class.java)
        val identifier = with(model) { "$flow/$stage" }
        if (model.shouldCache) {
            cache[identifier] = it
        }
        it
    }

    private fun SdUiModel.toSdUiRequest() = SdUiRequest(
        flow = flow,
        fromScreen = fromScreen,
        toScreen = toScreen,
        screenData = screenData,
    )
}