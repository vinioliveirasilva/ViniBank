package com.example.serverdriveui

import androidx.lifecycle.ViewModel
import com.example.serverdriveui.service.model.SdUiError
import com.example.serverdriveui.ui.action.actions.ContinueAction.Companion.CONTINUE_EFFECT_ID
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.example.serverdriveui.ui.state.ComponentStateManager
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
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

class SdUiViewModel(
    jsonModel: String,
    private val repository: SdUiRepository,
    private val componentParser: ComponentParser,
    private val globalStateManager: GlobalStateManager,
    private val componentStateManager: ComponentStateManager,
) : ViewModel(), LoaderComponent by LoaderComponentViewModel() {

    val components: MutableStateFlow<List<Component>> = MutableStateFlow(
        componentParser.parseList(
            data = Gson().fromJson(jsonModel, JsonObject::class.java),
            componentStateManager = componentStateManager
        )
    )

    private val _navigation: Channel<String> = Channel()
    val navigateOnSuccess = _navigation.receiveAsFlow()

    init {
        addCloseable(componentStateManager)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    fun initialize() = globalStateManager.getState<SdUiDestinationModel>(CONTINUE_EFFECT_ID)
        ?.flatMapConcat { destination ->
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
                    val errorFeedback =
                        Gson().fromJson(
                            error.message?.split("Network call failed: 400 ")?.last().orEmpty(),
                            SdUiError::class.java
                        )
                    componentStateManager.apply {
                        shouldUpdate = true
                        updatedStates.clear()
                    }
                    components.update {
                        componentParser.parseList(
                            data = JsonParser.parseString(errorFeedback.screen).asJsonObject,
                            componentStateManager = componentStateManager
                        )
                    }
                }
                .onStart { showLoader() }
                .onCompletion { hideLoader() }
                .map { _navigation.send(it) }
                .flowOn(Dispatchers.IO)
        }
}