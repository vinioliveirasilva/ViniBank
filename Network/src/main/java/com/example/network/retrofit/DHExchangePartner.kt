package com.example.network.retrofit

import java.security.KeyFactory
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.spec.AlgorithmParameterSpec
import java.security.spec.X509EncodedKeySpec
import javax.crypto.KeyAgreement
import javax.crypto.interfaces.DHPublicKey

class DHExchangePartner(
    private val algoSpec: AlgorithmParameterSpec,
    private val algoName: String = "DH",
) {

    private lateinit var keyAgreement: KeyAgreement

    private fun createPersonalDHKeypairAndInitAgreement(initKeyPairGenerator: (KeyPairGenerator) -> Unit): KeyPair {
        val keyPair = KeyPairGenerator.getInstance(algoName).apply {
            initKeyPairGenerator(this)
        }.generateKeyPair()
        initializeKeyAgreement(keyPair)
        return keyPair
    }

    fun createPersonalDHKeypairAndInitAgreement(): KeyPair =
        createPersonalDHKeypairAndInitAgreement { it.initialize(algoSpec) }

    fun createPublicKeyFromEncodedMaterial(encoded: ByteArray): DHPublicKey {
        val keyFactory = KeyFactory.getInstance(algoName)
        return keyFactory.generatePublic(X509EncodedKeySpec(encoded)) as DHPublicKey
    }

    private fun initializeKeyAgreement(keyPair: KeyPair) {
        keyAgreement = KeyAgreement.getInstance(algoName).apply {
            init(keyPair.private)
        }
    }

    fun phaseOne(partnerPublicKey: DHPublicKey) {
        keyAgreement.doPhase(partnerPublicKey, true)
    }

    fun generateSharedSecret(): ByteArray = keyAgreement.generateSecret()
}


fun ByteArray.asHex() = joinToString("") { "%02x".format(it) }