package com.vini.bank.initializers

import com.example.network.ktor.KtorInitializer

class CryptoBindInitializer(
    private val ktorInitializer: KtorInitializer
) : Initializer {
    override var hasBeenInitialized: Boolean = false


    override suspend fun initialize() {
        if (hasBeenInitialized) return
        super.initialize()
        ktorInitializer.abc()
        hasBeenInitialized = true
    }
}