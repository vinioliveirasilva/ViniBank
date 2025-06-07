package com.example.serverdriveui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverdriveui.ui.component.manager.Component
import com.example.serverdriveui.ui.component.manager.ComponentParser
import com.vini.designsystem.compose.loader.LoaderComponent
import com.vini.designsystem.compose.loader.LoaderComponentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class SdUiViewModel(
    private val model: SdUiModel,
    private val repository: SdUiRepository,
    private val componentParser: ComponentParser,
) : ViewModel(), LoaderComponent by LoaderComponentViewModel() {
    val components: MutableStateFlow<List<Component>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            repository
                .getScreen(model)
                .catch { println(it) }
                .onStart { showLoader() }
                .onCompletion { hideLoader() }
                .map { components.value = componentParser.parse(it.components) }
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)
        }

    }
}