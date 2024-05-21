package com.vini.featuresignup

import com.vini.storage.SecureStorage
import com.vini.storage.model.UserData

class SignUpRepository(
    private val secureStorage: SecureStorage,
) {
    fun doSignUp(userData: UserData) = secureStorage.saveUserData(userData)
}
