package com.vini.bank.initializers

interface Initializer {
    var hasBeenInitialized: Boolean
    suspend fun initialize() = Unit
}