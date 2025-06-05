package com.example.serverdriveui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.serverdriveui.ui.components.manager.Component
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
    private val screenId: String,
    private val screenData: String,
    private val repository: SdUiRepository,
) : ViewModel(), LoaderComponent by LoaderComponentViewModel() {
    val components: MutableStateFlow<List<Component>> = MutableStateFlow(emptyList())

    init {
        viewModelScope.launch {
            repository
                .getScreen(screenId, screenData)
                .catch {
                    println(it)
                    //should show error feedback with a retry button and close button
                }
                .onStart { showLoader() }
                .onCompletion { hideLoader() }
                .map { components.value = it }
                .flowOn(Dispatchers.IO)
                .launchIn(viewModelScope)
        }

    }
}