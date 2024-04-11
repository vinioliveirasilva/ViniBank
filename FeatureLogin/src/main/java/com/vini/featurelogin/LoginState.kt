package com.vini.featurelogin

data class LoginState(
    val email: String = "",
    val pass: String = "",
    var snackBarError: String? = null
)
