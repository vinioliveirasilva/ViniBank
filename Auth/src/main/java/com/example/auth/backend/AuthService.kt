package com.example.auth.backend

import com.example.network.Encrypt
import com.google.gson.annotations.SerializedName
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthService {
    @Encrypt
    @POST("/authenticate")
    fun authenticate(@Body user: User): Flow<UserAuthenticated>

    @Encrypt
    @GET("/logout")
    fun logout(): Flow<Any>
}

data class User(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
)

data class UserAuthenticated(
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("docNumber") val docNumber: String,
)
