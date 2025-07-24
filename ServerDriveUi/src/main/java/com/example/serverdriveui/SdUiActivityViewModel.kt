package com.example.serverdriveui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverdriveui.ui.state.ComponentStateManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

class SdUiActivityViewModel(
    private val flowId: String,
    private val screenData: JsonObject,
    private val repository: SdUiRepository,
    private val componentStateManager: ComponentStateManager,
    private val savedStateHandle: SavedStateHandle,
    private val closables: List<AutoCloseable>,
) : ViewModel() {

    private val _screenCaller: Channel<SdUiScreenRoute> = Channel()
    val screenCaller = _screenCaller.receiveAsFlow()

    override fun onCleared() {
        super.onCleared()
        closables.forEach { it.close() }
    }

    fun doOnStop() {
        componentStateManager.states.forEach { (id, stateFlow) ->
            savedStateHandle[id] = stateFlow.value
        }
    }

    init {
        savedStateHandle.keys().forEach {
            componentStateManager.registerState<Any?>(it, savedStateHandle[it])
        }

        repository
            .getScreen(
                SdUiModel(
                    flow = flowId,
                    screenData = screenData,
                )
            )
            .catch { emit(Json.decodeFromString<JsonObject>(it.message.orEmpty())) }
            .map { _screenCaller.send(SdUiScreenRoute(screenData = it)) }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}