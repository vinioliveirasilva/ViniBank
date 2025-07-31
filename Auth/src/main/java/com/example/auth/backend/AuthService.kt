package com.example.auth.backend

import com.example.network.Encrypt
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
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

@Serializable
data class User(
    @SerialName("email") val email: String,
    @SerialName("password") val password: String,
)

@Serializable
data class UserAuthenticated(
    @SerialName("email") val email: String,
    @SerialName("name") val name: String,
    @SerialName("docNumber") val docNumber: String,
)
