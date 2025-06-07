package com.vini.bank.initializers

import com.example.network.retrofit.RetrofitInitializer

class CryptoBindInitializer(
    private val retrofitInitializer: RetrofitInitializer
) : Initializer {
    override var hasBeenInitialized: Boolean = false


    override suspend fun initialize() {
        if (hasBeenInitialized) return
        super.initialize()
        retrofitInitializer.abc()
        hasBeenInitialized = true
    }
}