package com.vini.bank

import android.app.Activity
import androidx.activity.result.ActivityResult
import androidx.lifecycle.ViewModel
import com.vini.bank.initializers.Initializer
import com.vini.common.mvvm.sendInScope
import com.vini.storage.SessionStorage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LauncherViewModel(
    private val sessionStorage: SessionStorage,
    private val initializers: List<Initializer>,
    private val initializerScope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : ViewModel() {

    private val _event = Channel<LauncherUIEvent>()
    val event = _event.receiveAsFlow()

    private val _uiState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val uiState: StateFlow<Boolean> = _uiState

    fun doOnCreate() {
        initializerScope.launch {
            initializers.filter { it.hasBeenInitialized.not() }.also { toBeInitialized ->
                _uiState.update { toBeInitialized.isNotEmpty() }
                toBeInitialized.forEach { initializer -> initializer.initialize() }
            }
        }.invokeOnCompletion {
            _event.sendInScope(
                this,
                if (sessionStorage.isAuthenticated()) {
                    LauncherUIEvent.OpenHome
                } else {
                    LauncherUIEvent.OpenLogin
                }
            )
        }
    }

    fun doOnLoginRouteResult(activityResult: ActivityResult) =
        _event.sendInScope(
            this,
            when (activityResult.resultCode) {
                Activity.RESULT_OK -> LauncherUIEvent.OpenHome
                else -> LauncherUIEvent.Finish
            }
        )
}