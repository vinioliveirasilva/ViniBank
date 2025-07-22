package com.example.network

interface KeyExchanger {
    fun init()
    fun getEncodedPublicKey() : ByteArray
    fun createPublicKeyAndRunPhaseOne(encoded: ByteArray)
    fun generateSecret() : String
}