package com.example.auth.firebase

import com.example.auth.AuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow

class FirebaseAuthProvider(
    private val provider: FirebaseAuth = Firebase.auth,
) : AuthProvider {
    override fun isAuthenticated(): Boolean = provider.currentUser != null

    override fun authenticate(username: String, password: String) = callbackFlow {
        val result = async { provider.signInWithEmailAndPassword(username, password) }.await()
        send(result.isSuccessful)
        awaitClose()
    }

    override fun logout() = flow {
        provider.signOut()
        emit(true)
    }
}