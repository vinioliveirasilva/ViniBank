package com.example.serverdriveui.ui.action.manager

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class ActionStateManager(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : AutoCloseable {
    private val state: Channel<Map<String, Any?>> = Channel()

    fun updateStates(states: Map<String, Any?>) {
        scope.launch { state.send(states) }
    }

    fun getState() = state.receiveAsFlow()

    override fun close() {
        scope.cancel()
    }
}