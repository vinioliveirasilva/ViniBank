package com.example.serverdriveui.ui.component.components.sdui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverdriveui.SdUiModel
import com.example.serverdriveui.SdUiRepository
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.vini.designsystem.compose.loader.LoaderComponent
import com.vini.designsystem.compose.loader.LoaderComponentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

class SdUiComponentViewModel(
    private val repository: SdUiRepository,
    private val componentParser: ComponentParser,
) : ViewModel(), LoaderComponent by LoaderComponentViewModel() {

    val components: MutableStateFlow<List<Component>> = MutableStateFlow(emptyList())
    private var lastScreenId: String = ""
    private var lastJob: Job? = null

    fun initialize(flowId: Flow<String>, screenId: Flow<String>, screenData: Flow<JsonObject>) {
        combine(flowId, screenId, screenData) { flow, screen, screenData ->
            setupScreen(flow, screen, screenData)
        }.launchIn(viewModelScope)
    }

    private fun setupScreen(flow: String, screen: String, screenData: JsonObject) {
        lastJob?.cancel()
        lastJob = repository.getScreen(
            SdUiModel(
                flow = flow,
                toScreen = screen,
                screenData = screenData,
                fromScreen = lastScreenId
            )
        )
            .catch { emit(Json.decodeFromString(it.message.orEmpty())) }
            .map { response ->
                lastScreenId = screen
                components.update {
                    componentParser.parseList(
                        data = response
                    )
                }
            }
            .onStart { showLoader() }
            .onCompletion { hideLoader() }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}