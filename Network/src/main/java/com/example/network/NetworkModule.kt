package com.example.network

import com.example.network.ktor.KtorHttpClientProvider
import com.example.network.ktor.KtorInitializer
import org.koin.dsl.module

val NetworkModule = module {

    single<EncodeProvider> {
        EncodeProvider()
    }

    single {
        KtorHttpClientProvider(
            encoderProvider = get(),
            keyExchangeManager = get()
        )
    }

    single {
        KtorInitializer(
            provider = get()
        )
    }
}