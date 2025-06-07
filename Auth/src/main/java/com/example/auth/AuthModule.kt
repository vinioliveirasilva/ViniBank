package com.example.auth

import com.example.auth.backend.AuthService
import com.example.auth.backend.DefaultAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.dsl.module
import retrofit2.Retrofit

val AuthModule = module {
    //singleOf(::FirebaseAuthProvider) bind AuthProvider::class
    //singleOf(::DefaultAuthProvider) bind AuthProvider::class
    single<AuthProvider> { DefaultAuthProvider(service = get()) }
    single<FirebaseAuth> { Firebase.auth }
    factory<AuthService> { get<Retrofit>().create(AuthService::class.java) }
}