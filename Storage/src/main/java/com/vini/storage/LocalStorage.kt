package com.vini.storage

import android.content.Context
import com.vini.common.gson.GsonProvider

class LocalStorage(private val context: Context, private val gsonProvider: GsonProvider) {

    private val sharedPref by lazy {
        context.getSharedPreferences("login", Context.MODE_PRIVATE)
    }

    fun isAuthenticated(): Boolean {
        return sharedPref.getBoolean("auth", false)
    }

    fun authenticate(email: String, pass: String): Boolean? {
        return if (email == "vinioliveirasilva@hotmail.com" && pass == "123") {
            sharedPref.edit().putBoolean("auth", true).apply()
            true
        } else {
            null
        }
    }

    fun <T> save(key: String, value: T) = when (value) {
        is String -> sharedPref.edit().putString(key, value).apply()
        else -> {}
    }

    fun getString(key: String): String = sharedPref.getString(key, null).orEmpty()
}