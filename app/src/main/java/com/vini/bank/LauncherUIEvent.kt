package com.vini.bank

sealed class LauncherUIEvent {
    data object Finish : LauncherUIEvent()
    data object OpenLogin : LauncherUIEvent()
    data object OpenHome : LauncherUIEvent()
}