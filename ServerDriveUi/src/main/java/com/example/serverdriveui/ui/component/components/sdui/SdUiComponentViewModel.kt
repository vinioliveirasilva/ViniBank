package com.example.serverdriveui.ui.component.components.sdui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverdriveui.SdUiModel
import com.example.serverdriveui.SdUiRepository
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.vini.designsystem.compose.loader.LoaderComponent
import com.vini.designsystem.compose.loader.LoaderComponentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combineTransform
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.update

class SdUiComponentViewModel(
    private val repository: SdUiRepository,
    private val componentParser: ComponentParser,
    private val componentStateManager: ComponentStateManager,
) : ViewModel(), LoaderComponent by LoaderComponentViewModel() {

    val components: MutableStateFlow<List<Component>> = MutableStateFlow(emptyList())
    private var lastScreenId: String = ""

    init {
        addCloseable(componentStateManager)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun initialize(flowId: Flow<String>, screenId: Flow<String>, screenData: Flow<String>) {
        //TODO cancel repository call if screenId or flowId changes
        combineTransform(flowId, screenId, screenData) { flow, screen, screenData ->
            println(screenData)
            repository.getScreen(
                SdUiModel(
                    flow = flow,
                    toScreen = screen,
                    screenData = screenData,
                    fromScreen = lastScreenId
                )
            )
                .catch { emit(it.message.orEmpty()) }
                .map {
                    lastScreenId = screen
                    it
                }
                .onStart { showLoader() }
                .onCompletion { hideLoader() }
                .collect { emit(it) }
        }
            .map { response ->
                components.update {
                    componentParser.parseList(
                        data = Gson().fromJson(response, JsonObject::class.java),
                        componentStateManager = componentStateManager
                    )
                }
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}