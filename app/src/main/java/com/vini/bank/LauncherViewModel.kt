package com.vini.bank

import androidx.lifecycle.ViewModel
import com.vini.common.mvvm.sendInScope
import com.vini.storage.LocalStorage
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LauncherViewModel(private val localStorage: com.vini.storage.LocalStorage) : ViewModel() {

    private val _event = Channel<LauncherUIEvent>()
    val event = _event.receiveAsFlow()
    fun doOnCreate() {
        _event.sendInScope(
            this,
            if (localStorage.isAuthenticated()) {
                LauncherUIEvent.OpenHome
            } else {
                LauncherUIEvent.OpenLogin
            }
        )

    }
}