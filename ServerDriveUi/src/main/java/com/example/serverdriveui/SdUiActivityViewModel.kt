package com.example.serverdriveui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverdriveui.ui.action.actions.NavigateAction.Companion.NAVIGATE_EFFECT_ID
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
    private val globalStateManager: GlobalStateManager,
    private val closables: List<AutoCloseable>
) : ViewModel() {

    private val _successNavigation: Channel<SdUiRoute> = Channel()
    private val _successNavigation1: Channel<com.example.router.routes.SdUiRoute> = Channel()
    val navigateOnSuccess = _successNavigation.receiveAsFlow()
    val navigateOnSuccess1 = _successNavigation1.receiveAsFlow()

    override fun onCleared() {
        super.onCleared()
        closables.forEach { it.close() }
    }

    init {
        globalStateManager.registerState(NAVIGATE_EFFECT_ID)

        globalStateManager.getState<com.example.router.routes.SdUiRoute>(NAVIGATE_EFFECT_ID)?.map {
            _successNavigation1.send(it)
        }?.launchIn(viewModelScope)

        repository
            .getScreen(
                SdUiModel(
                    flow = flowId,
                    screenData = screenData,
                )
            )
            .catch { emit(Json.decodeFromString<JsonObject>(it.message.orEmpty())) }
            .map {
                _successNavigation.send(SdUiRoute(screenData = it))
            }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}