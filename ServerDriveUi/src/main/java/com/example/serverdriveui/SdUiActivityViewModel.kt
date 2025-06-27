package com.example.serverdriveui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverdriveui.ui.action.actions.ContinueAction.Companion.CONTINUE_EFFECT_ID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

class SdUiActivityViewModel(
    private val flowId: String,
    private val screenData: String,
    private val repository: SdUiRepository,
    private val globalStateManager: GlobalStateManager,
) : ViewModel() {

    private val _successNavigation: Channel<SdUiRoute> = Channel()
    val navigateOnSuccess = _successNavigation.receiveAsFlow()

    init {
        addCloseable(globalStateManager)

        globalStateManager.registerState(CONTINUE_EFFECT_ID)

        repository
            .getScreen(
                SdUiModel(
                    flowId = flowId,
                    screenData = screenData,
                )
            )
            .catch { emit(it.message.orEmpty()) }
            .map { _successNavigation.send(SdUiRoute(screenData = it)) }
            .flowOn(Dispatchers.IO)
            .launchIn(viewModelScope)
    }
}