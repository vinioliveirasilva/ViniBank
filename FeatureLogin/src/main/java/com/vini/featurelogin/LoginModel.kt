package com.vini.featurelogin

sealed class LoginUIEvent {
    data object DoOnDismissSnackBar : LoginUIEvent()
    data class DoOnSignUp(val resultCode: Int) : LoginUIEvent()
    class DoOnPassChange(val pass: String) : LoginUIEvent()
    class DoOnEmailChange(val email: String) : LoginUIEvent()
    class DoOnPasswordVisibilityChange(val isVisible: Boolean) : LoginUIEvent()
    data object DoOnLogin : LoginUIEvent()
}

sealed class LoginVMEvent {
    data object BusinessSuccess : LoginVMEvent()
}

data class LoginState(
    val email: String = "",
    val pass: String = "",
    val snackBarError: String? = null,
    val isPasswordVisible: Boolean = false,
)
