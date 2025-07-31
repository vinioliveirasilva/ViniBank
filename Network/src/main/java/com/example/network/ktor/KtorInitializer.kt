package com.example.network.ktor

class KtorInitializer(
    private val provider: KtorHttpClientProvider,
) {

    suspend fun abc() = provider.startHandShake()
}