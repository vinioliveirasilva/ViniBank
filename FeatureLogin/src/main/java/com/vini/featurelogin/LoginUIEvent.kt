package com.vini.featurelogin

sealed class LoginUIEvent {
    data object DoOnDismissSnackBar : LoginUIEvent()

    data class DoOnSignUp(val resultCode: Int) : LoginUIEvent()
    data class DoOnLogin(val email: String, val pass: String) : LoginUIEvent()
}

sealed class LoginVMEvent {
    data object BusinessSuccess : LoginVMEvent()
}
