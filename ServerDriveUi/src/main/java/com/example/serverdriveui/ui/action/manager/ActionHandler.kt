package com.example.serverdriveui.ui.action.manager

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.router.routes.SdUiRoute
import com.example.serverdriveui.GlobalStateManager
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

class ActionHandler(
    private val globalStateManager: GlobalStateManager,
    private val actionStateManager: ActionStateManager,
    private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _routerCaller: Channel<SdUiRoute> = Channel()
    val routeCaller = _routerCaller.receiveAsFlow()

    private var requestedData: Map<String, String>
        get() { return savedStateHandle["requestedData"] ?: emptyMap() }
        set(value) { savedStateHandle.set("requestedData", value) }

    private var actionIdToRun: String?
        get() { return savedStateHandle["actionIdToRun"] }
        set(value) { savedStateHandle.set("actionIdToRun", value) }

    init {
        globalStateManager.getNavArgs().map {
            actionIdToRun = it.actionIdToRun
            requestedData = it.requestedData
            _routerCaller.send(it.route)
        }.launchIn(viewModelScope)
    }

    fun handleActivityResult(
        resultData: Map<String, String>,
    ) {
        if (resultData.isEmpty()) return

        val states =  requestedData.asSequence().associate { (stateId, resultId) ->
            stateId to resultData[resultId]
        }

        val actionAndState = actionIdToRun?.let { states.plus(it to true) } ?: states

        actionStateManager.updateStates(actionAndState)
    }
}