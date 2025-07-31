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
import kotlinx.coroutines.flow.asStateFlow
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

    private val internalComponents = MutableStateFlow(emptyList<Component>())
    val components = internalComponents.asStateFlow()
    private var lastJob: Job? = null

    fun initialize(flowId: Flow<String>, screenId: Flow<String>, screenData: Flow<JsonObject>, fromScreen: Flow<String>) {
        combine(flowId, screenId, screenData, fromScreen) { flow, screen, screenData, fromScreen ->
            setupScreen(flow, screen, screenData, fromScreen)
        }.launchIn(viewModelScope)
    }

    private fun setupScreen(flow: String, screen: String, screenData: JsonObject, fromScreen: String) {
        lastJob?.cancel()
        lastJob = repository.getScreen(
            SdUiModel(
                flow = flow,
                toScreen = screen,
                screenData = screenData,
                fromScreen = fromScreen
            )
        )
            .catch { emit(it.message.toJson()) }
            .map { response ->
                internalComponents.update {
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

    private fun String?.toJson() = Json.decodeFromString<JsonObject>(this.orEmpty())
}