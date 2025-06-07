package com.vini.featurelogin

sealed class LoginUIEvent {
    data object DoOnDismissSnackBar : LoginUIEvent()
    data class DoOnSignUpResult(val resultCode: Int, val login: String, val pass: String) : LoginUIEvent()
    class DoOnPassChange(val pass: String) : LoginUIEvent()
    class DoOnEmailChange(val email: String) : LoginUIEvent()
    class DoOnPasswordVisibilityChange(val isVisible: Boolean) : LoginUIEvent()
    data object DoOnLogin : LoginUIEvent()
    object DoOnSignUp : LoginUIEvent()
    object DoOnSdUi : LoginUIEvent()
}

sealed class LoginVMEvent {
    data object BusinessSuccess : LoginVMEvent()
    object NavigateToSignUp : LoginVMEvent()
    object NavigateToSdUi : LoginVMEvent()
}

data class LoginState(
    val email: String = "",
    val pass: String = "",
    val snackBarError: String? = null,
    val isPasswordVisible: Boolean = false,
)
