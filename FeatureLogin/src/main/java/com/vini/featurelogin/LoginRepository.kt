package com.vini.featurelogin

import com.vini.storage.LocalStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class LoginRepository(private val localStorage: LocalStorage) {
    fun doLogin(email: String, pass: String) = flow {
        delay(5000L)
        val wasSuccess = localStorage.authenticate(email, pass)
        emit(wasSuccess ?: throw Exception("User Not Found"))
    }
}