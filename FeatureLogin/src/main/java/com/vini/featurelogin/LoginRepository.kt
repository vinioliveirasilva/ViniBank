package com.vini.featurelogin

import com.example.auth.AuthProvider

class LoginRepository(
    private val authProvider: AuthProvider,
) {
    fun doLogin(email: String, pass: String) = authProvider.authenticate(email, pass)
}

