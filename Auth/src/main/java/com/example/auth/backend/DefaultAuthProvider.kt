package com.example.auth.backend

import com.example.auth.AuthProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class DefaultAuthProvider(
    private val service: AuthService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : AuthProvider {

    override fun authenticate(
        username: String,
        password: String
    ): Flow<Boolean> = service
        .authenticate(user = User(username, password))
        .map { true }
        .flowOn(dispatcher)

    override fun isAuthenticated(): Boolean {
        return false
    }

    override fun logout() = service.logout().map { true }.flowOn(dispatcher)
}