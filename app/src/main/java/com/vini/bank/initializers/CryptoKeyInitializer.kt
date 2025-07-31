package com.vini.bank.initializers

import com.example.network.FastKeyExchangeManager
import com.example.network.KeyExchangeManager

class CryptoKeyInitializer(
    private val exchangeManager: KeyExchangeManager,
    private val fastKeyExchangeManager: FastKeyExchangeManager,
) : Initializer {

    override var hasBeenInitialized: Boolean = false

    override suspend fun initialize() {
        if (hasBeenInitialized) return
        super.initialize()
        //exchangeManager.init()
        fastKeyExchangeManager.init()
        hasBeenInitialized = true
    }
}