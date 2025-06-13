package com.example.serverdriveui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverdriveui.service.model.ScreenModel
import com.example.serverdriveui.ui.actions.ContinueAction.Companion.CONTINUE_EFFECT_ID
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.example.serverdriveui.ui.validator.manager.Validator
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

class SdUiActivityViewModel(
    private val flowId: String,
    private val repository: SdUiRepository,
    private val validators: List<Validator>,
    private val globalStateManager: GlobalStateManager,
    private val componentStateManager: ComponentStateManager
) : ViewModel() {

    private val _errorNavigation: Channel<Boolean> = Channel()
    val navigateOnError = _errorNavigation.receiveAsFlow()

    private val _successNavigation: Channel<SdUiDestination2> = Channel()
    val navigateOnSuccess = _successNavigation.receiveAsFlow()

    val startDestination = MutableStateFlow<@Serializable Any>(LoaderDestination)

    init {
        viewModelScope.launch {
            globalStateManager.registerState(CONTINUE_EFFECT_ID)
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
                    _errorNavigation.send(true)
                    emit(screen)
                    //should show error feedback with a retry button and close button
                }
                .map {
                    delay(5000)
                    _successNavigation.send(SdUiDestination2(it))
                    //startDestination.value = SdUiDestination2(it)
                }
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)
        }
    }

    override fun onCleared() {
        validators.forEach { it.close() }
        globalStateManager.close()
        componentStateManager.close()
    }
}