package com.example.network.retrofit

import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.spec.X509EncodedKeySpec
import javax.crypto.KeyAgreement
import javax.crypto.interfaces.DHPublicKey
import javax.crypto.spec.DHParameterSpec

class DHExchangePartner(private val algoSpec: DHParameterSpec?) {

    private lateinit var keyAgreement: KeyAgreement

    private fun createPersonalDHKeypairAndInitAgreement(initKeyPairGenerator: (KeyPairGenerator) -> Unit): KeyPair {
        val keyPair = KeyPairGenerator.getInstance("DH").apply {
            initKeyPairGenerator(this)
        }.generateKeyPair()
        initializeKeyAgreement(keyPair)
        return keyPair
    }

    fun createPersonalDHKeypairAndInitAgreement(): KeyPair =
        createPersonalDHKeypairAndInitAgreement { it.initialize(algoSpec) }

    fun createPublicKeyFromEncodedMaterial(encoded: ByteArray): DHPublicKey {
        val keyFactory = KeyFactory.getInstance("DH")
        return keyFactory.generatePublic(X509EncodedKeySpec(encoded)) as DHPublicKey
    }

    private fun initializeKeyAgreement(keyPair: KeyPair) {
        keyAgreement = KeyAgreement.getInstance("DH").apply {
            init(keyPair.private)
        }
    }

    fun phaseOne(partnerPublicKey: DHPublicKey) {
        keyAgreement.doPhase(partnerPublicKey, true)
    }

    fun generateSharedSecret(): ByteArray = keyAgreement.generateSecret()
}


fun ByteArray.asHex() = joinToString("") { "%02x".format(it) }