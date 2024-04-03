package com.vini.featurelogin

sealed class LoginUIEvent {
    class SetupEmail(val email: String) : LoginUIEvent()
    class ShowAlert(val message: String?) : LoginUIEvent()
    data object ShowLoader : LoginUIEvent()
    data object HideLoader : LoginUIEvent()
    data object BusinessSuccess : LoginUIEvent()
}
