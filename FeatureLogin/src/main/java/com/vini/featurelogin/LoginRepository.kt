package com.vini.featurelogin

import com.vini.storage.SecureStorage
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.onStart

class LoginRepository(
    private val secureStorage: SecureStorage,
) {
    fun doLogin(email: String, pass: String) = secureStorage.getUserData(email, pass).onStart {
        delay(500)
    }
}

