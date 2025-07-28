package com.example.serverdriveui

import com.example.serverdriveui.ui.action.actions.NavigationArg
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class GlobalStateManager(
    val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)
) : AutoCloseable {
    private val internalNavArgs = Channel<NavigationArg>()
    private val internalDestination = Channel<SdUiDestinationModel>()

    fun getNavArgs() = internalNavArgs.receiveAsFlow()
    fun setNavArgs(navArgs: NavigationArg) = scope.launch { internalNavArgs.send(navArgs) }

    fun getDestination() = internalDestination.receiveAsFlow()
    fun setDestination(destination: SdUiDestinationModel) = scope.launch { internalDestination.send(destination) }

    override fun close() {
        scope.cancel()
    }
}