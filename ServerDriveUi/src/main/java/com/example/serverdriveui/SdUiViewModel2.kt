package com.example.serverdriveui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverdriveui.service.model.ScreenModel
import com.example.serverdriveui.ui.actions.ContinueAction.Companion.CONTINUE_EFFECT_ID
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.google.gson.Gson
import com.vini.designsystem.compose.loader.LoaderComponent
import com.vini.designsystem.compose.loader.LoaderComponentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

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

    init {
        viewModelScope.launch {
            stateManager.getState<SdUiDestination?>(CONTINUE_EFFECT_ID)?.collect { destination ->
                println("$destination ${this@SdUiViewModel2}")
                if(destination == null) return@collect
                repository
                    .getScreen(
                        SdUiModel(
                            destination.flowId,
                            destination.screenId,
                            destination.screenData,
                            destination.lastScreenId
                        )
                    )
                    .catch {
                        //println(it)
                        val screen =
                            Gson().fromJson<ScreenModel>(it.message, ScreenModel::class.java)
                        val code = 400
                        val message = "Email ja cadastrado"
                        components.value = componentParser.parse(screen.components)
                        //should show error feedback with a retry button and close button
                    }
                    .onStart { showLoader() }
                    .onCompletion { hideLoader() }
                    .map {
                        delay(500)
                        navigateOnSuccess.emit(it)
                    }
                    .flowOn(Dispatchers.IO)
                    .launchIn(viewModelScope)
            }
        }
    }
}