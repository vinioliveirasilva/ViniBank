package com.example.auth

import kotlinx.coroutines.flow.Flow

interface AuthProvider {
    fun authenticate(username: String, password: String): Flow<Boolean>
    fun isAuthenticated(): Boolean
    fun logout() : Flow<Boolean>
}