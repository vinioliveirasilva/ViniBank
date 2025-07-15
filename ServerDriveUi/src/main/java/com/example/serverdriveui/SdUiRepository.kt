package com.example.serverdriveui

import com.example.serverdriveui.service.SdUiService
import com.example.serverdriveui.service.model.SdUiRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

@Serializable
private data class ScreenModel(
    @SerialName("shouldCache")
    val shouldCache: Boolean = true,
    @SerialName("flow")
    val flow: String,
    @SerialName("stage")
    val stage: String,
    @SerialName("template")
    val template: String,
    @SerialName("version")
    val version: String,
)

class SdUiRepository(
    private val sdUiService: SdUiService,
) {
    private val cache = mutableMapOf<String, JsonObject>()

    fun getScreen(
        model: SdUiModel
    ): Flow<JsonObject> {
        val identifier = with(model) { "$flow/$toScreen" }
        val hasCache = cache.containsKey(identifier)
        if (hasCache) {
            return cache[identifier]?.let { flowOf(it).abc() }
                ?: sdUiService.getScreenModel(
                    request = model.toSdUiRequest()
                ).abc()
        }

        return sdUiService.getScreenModel(
            request = model.toSdUiRequest()
        ).abc()
    }

    private fun Flow<JsonObject>.abc() = map {
        val model = Json { ignoreUnknownKeys = true }.decodeFromJsonElement<ScreenModel>(it)
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