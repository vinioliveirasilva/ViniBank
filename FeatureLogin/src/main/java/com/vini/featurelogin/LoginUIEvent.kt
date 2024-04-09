package com.vini.featurelogin

sealed class LoginUIEvent {
    class ShowAlert(val message: String?) : LoginUIEvent()
    data object BusinessSuccess : LoginUIEvent()
    data object OpenSignUp : LoginUIEvent()
    data object Empty : LoginUIEvent()
}
