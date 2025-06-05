package com.vini.storage

import com.example.auth.AuthProvider

class SessionStorage(
    private val auth: AuthProvider,
) {
    fun isAuthenticated(): Boolean = auth.isAuthenticated()
}