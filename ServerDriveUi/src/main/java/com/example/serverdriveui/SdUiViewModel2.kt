package com.example.serverdriveui

import androidx.lifecycle.ViewModel
import com.example.serverdriveui.service.model.ScreenModel
import com.example.serverdriveui.service.model.SdUiError
import com.example.serverdriveui.ui.actions.ContinueAction.Companion.CONTINUE_EFFECT_ID
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.google.gson.Gson
import com.vini.designsystem.compose.loader.LoaderComponent
import com.vini.designsystem.compose.loader.LoaderComponentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class SdUiViewModel2(
    private val model: ScreenModel,
    private val repository: SdUiRepository,
    private val componentParser: ComponentParser,
    private val stateManager: GlobalStateManager,
) : ViewModel(), LoaderComponent by LoaderComponentViewModel() {

    val components: MutableStateFlow<List<Component>> = MutableStateFlow(
        componentParser.parse(model.components)
    )

    val navigateOnSuccess: MutableSharedFlow<ScreenModel?> = MutableSharedFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    fun initialize() = stateManager.getState<SdUiDestination>(CONTINUE_EFFECT_ID)?.flatMapConcat { destination ->
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
                    Gson().fromJson<SdUiError>(error.message?.split("Network call failed: 400 ")?.last().orEmpty(), SdUiError::class.java)
                components.value = componentParser.parse(errorFeedback.screen.components)
            }
            .onStart { showLoader() }
            .onCompletion { hideLoader() }
            .map {
                delay(500)
                navigateOnSuccess.emit(it)
            }
            .flowOn(Dispatchers.IO)
    }
}