package com.vini.bank.initializers

import com.example.network.KeyExchangeManager

class CryptoKeyInitializer(private val exchangeManager: KeyExchangeManager) : Initializer {

    override var hasBeenInitialized: Boolean = false

    override suspend fun initialize() {
        if (hasBeenInitialized) return
        super.initialize()
        exchangeManager.init()
        hasBeenInitialized = true
    }
}