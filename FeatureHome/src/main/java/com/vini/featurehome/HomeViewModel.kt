package com.vini.featurehome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.auth.AuthProvider
import com.vini.common.mvvm.sendInScope
import com.vini.designsystem.compose.loader.LoaderComponent
import com.vini.designsystem.compose.loader.LoaderComponentViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow

sealed class HomeEvent {
    data object Logout : HomeEvent()
    data object GoToUser : HomeEvent()
    data class GoToContent(val contentIndex: Int) : HomeEvent()
}

sealed class HomeUiEvent {
    data object DoOnLogout : HomeUiEvent()
}

class HomeViewModel(
    private val authProvider: AuthProvider,
    private val homeContentProvider: HomeContentProviderImpl,
) : ViewModel(), LoaderComponent by LoaderComponentViewModel() {

    val selectedContent: MutableStateFlow<HomeContentModel> =
        MutableStateFlow(homeContentProvider.getContent())

    private val _uiEvent = Channel<HomeUiEvent>()
    val uiEvent: Flow<HomeUiEvent> = _uiEvent.receiveAsFlow()

    fun handleEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.Logout -> doLogout()
            is HomeEvent.GoToUser -> TODO()
            is HomeEvent.GoToContent -> selectedContent.value =
                homeContentProvider.getContent(event.contentIndex)
        }
    }

    private fun doLogout() = authProvider
        .logout()
        .catch { println(it) }
        .onStart { showLoader() }
        .onCompletion { hideLoader() }
        .onEach { _uiEvent.sendInScope(this@HomeViewModel, HomeUiEvent.DoOnLogout) }
        .launchIn(viewModelScope)
}