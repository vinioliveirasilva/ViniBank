package com.vini.bank.initializers

import com.example.network.ktor.KtorInitializer
import com.example.network.retrofit.RetrofitInitializer

class CryptoBindInitializer(
    private val retrofitInitializer: RetrofitInitializer,
    private val ktorInitializer: KtorInitializer
) : Initializer {
    override var hasBeenInitialized: Boolean = false


    override suspend fun initialize() {
        if (hasBeenInitialized) return
        super.initialize()
        ktorInitializer.abc()
        //retrofitInitializer.abc()
        hasBeenInitialized = true
    }
}