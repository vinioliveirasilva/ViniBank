package com.vini.bank.initializers

import com.example.network.FastKeyExchangeManager

class CryptoKeyInitializer(
    private val fastKeyExchangeManager: FastKeyExchangeManager,
) : Initializer {

    override var hasBeenInitialized: Boolean = false

    override suspend fun initialize() {
        if (hasBeenInitialized) return
        super.initialize()
        fastKeyExchangeManager.init()
        hasBeenInitialized = true
    }
}