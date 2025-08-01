package com.example.serverdriveui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.serverdriveui.service.model.SdUiError
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.SavableComponentStateManager
import com.vini.designsystem.compose.loader.LoaderComponent
import com.vini.designsystem.compose.loader.LoaderComponentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject

class SdUiViewModel(
    jsonModel: JsonObject,
    private val repository: SdUiRepository,
    private val componentParser: ComponentParser,
    private val globalStateManager: GlobalStateManager,
    private val savableComponentStateManager: SavableComponentStateManager,
    private val closables: List<AutoCloseable>,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel(), LoaderComponent by LoaderComponentViewModel() {

    val components: MutableStateFlow<List<Component>> = MutableStateFlow(
        componentParser.parseList(
            data = jsonModel
        )
    )

    override fun onCleared() {
        super.onCleared()
        closables.forEach {
            it.close()
        }
    }

    private val _navigation: Channel<JsonObject> = Channel()
    val navigateOnSuccess = _navigation.receiveAsFlow()

    init {
        savableComponentStateManager.loadState(savedStateHandle)
    }

    fun doOnStop() {
        savableComponentStateManager.saveState(savedStateHandle)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun initialize() = globalStateManager.getDestination().flatMapConcat { destination ->
        repository
            .getScreen(
                SdUiModel(
                    destination.flowId,
                    destination.screenId,
                    destination.screenData,
                    destination.lastScreenId
                )
            )
            .catch { error ->
                val errorFeedback = Json.decodeFromString<SdUiError>(
                    error.message?.split("Network call failed: 400 ")?.last().orEmpty(),
                )

                componentParser.setupForError {
                    val newComponents = parseList(errorFeedback.screen)
                    components.update { newComponents }
                }
            }
            .onStart { showLoader() }
            .onCompletion { hideLoader() }
            .map { _navigation.send(it) }
            .flowOn(Dispatchers.IO)
    }
}