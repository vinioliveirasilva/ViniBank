package com.vini.storage

import android.content.Context
//import com.vini.featurelogin.model.UserData

class LocalStorage(private val context: Context, private val gsonProvider: com.vini.common.gson.GsonProvider) {

    private val sharedPref by lazy {
        context.getSharedPreferences("login", Context.MODE_PRIVATE)
    }

    fun isAuthenticated() : Boolean {
        return sharedPref.getBoolean("auth", false)
    }

    fun authenticate(email: String, pass: String) : Boolean? {
        return if(email == "vinioliveirasilva@hotmail.com" && pass == "123") {
            sharedPref.edit().putBoolean("auth", true).apply()
            true
        } else {
            null
        }
    }
    /*
    fun saveUserData(userData: UserData) {
        sharedPref.edit().putString("userData", gsonProvider.toJson(userData)).apply()
    }

    fun getSavedUserData(email: String, pass: String): UserData? {
        return if(email == "vinioliveirasilva@hotmail.com" && pass == "123") {
            UserData(name = "Vinicius Oliveira", email = email)
        } else {
            null
        }
    }

     */
}