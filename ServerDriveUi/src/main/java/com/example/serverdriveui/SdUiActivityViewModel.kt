package com.example.serverdriveui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverdriveui.service.model.ScreenModel
import com.example.serverdriveui.ui.actions.ContinueAction.Companion.CONTINUE_EFFECT_ID
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SdUiActivityViewModel(
    private val flowId: String,
    private val repository: SdUiRepository,
    private val stateManager: GlobalStateManager,
) : ViewModel() {
    val navigateOnError: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val navigateOnSuccess: MutableStateFlow<SdUiDestination2?> = MutableStateFlow(null)

    init {
        viewModelScope.launch {
            stateManager.registerState(CONTINUE_EFFECT_ID)
        }

        viewModelScope.launch {
            repository
                .getScreen(
                    SdUiModel(
                        flowId = flowId,
                        screenId = "",
                        screenData = "{}",
                        lastScreenId = ""
                    )
                )
                .catch {
                    val screen = Gson().fromJson<ScreenModel>(it.message, ScreenModel::class.java)
                    navigateOnError.update { true }
                    emit(screen)
                    //should show error feedback with a retry button and close button
                }
                .map { navigateOnSuccess.value = SdUiDestination2(it) }
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)
        }
    }
}