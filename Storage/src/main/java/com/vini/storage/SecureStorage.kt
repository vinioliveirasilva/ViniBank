package com.vini.storage

import com.vini.common.gson.GsonProvider
import com.vini.storage.model.UserData
import kotlinx.coroutines.flow.flow

class SecureStorage(
    private val cryptoManager: CryptographyProvider,
    private val localStorage: LocalStorage,
    private val gsonProvider: GsonProvider,
) {
    fun saveUserData(userData: UserData) = flow {
        val securePassword = cryptoManager.hash(userData.password)
        val secureEmail = cryptoManager.hash(userData.email)
        val updatedUserData = userData.copy(password = securePassword)
        val secureData = cryptoManager.encrypt(gsonProvider.toJson(updatedUserData))

        localStorage.save(TAG.plus(secureEmail), secureData)
        emit(true)
    }

    fun getUserData(email: String, password: String) = flow {
        val securePassword = cryptoManager.hash(password)
        val secureEmail = cryptoManager.hash(email)

        val secureUserData = localStorage.getString(TAG.plus(secureEmail))
        if (secureUserData.isEmpty()) {
            throw Exception("Email not found")
        }

        val userDataSerialized = cryptoManager.decrypt(secureUserData)
        println(userDataSerialized)
        if (userDataSerialized.isEmpty()) {
            throw Exception("UserData not found")
        }

        val userData = gsonProvider.fromJson(userDataSerialized, UserData::class.java)
        if (userData.password != securePassword) {
            throw Exception("Password not match")
        }

        emit(true)
    }

    private companion object {
        const val TAG = "SecureStorage"
    }
}