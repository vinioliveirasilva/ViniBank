package com.example.network

import com.example.network.retrofit.DHExchangePartner
import com.example.network.retrofit.asHex
import com.vini.common.Benchmark
import java.security.AlgorithmParameterGenerator
import java.security.KeyPair
import java.security.SecureRandom
import javax.crypto.spec.DHParameterSpec

class KeyExchangeManager() : KeyExchanger {
    private lateinit var exchangePartner: DHExchangePartner
    lateinit var keyPair: KeyPair
    private lateinit var encodedPublicKey: ByteArray

    override fun init() = Benchmark.bench("Cripto") {
        val algoName = "DH"
        AlgorithmParameterGenerator.getInstance(algoName).apply {
            Benchmark.bench("Init") { init(512, SecureRandom()) }
            exchangePartner = DHExchangePartner(
                algoSpec = Benchmark.bench("Spec") {
                    generateParameters().getParameterSpec(
                        DHParameterSpec::class.java
                    )
                },
                algoName = algoName
            )
            keyPair =
                Benchmark.bench("keyPair") { exchangePartner.createPersonalDHKeypairAndInitAgreement() }
            encodedPublicKey = keyPair.public.encoded
        }
        Unit
    }

    override fun getEncodedPublicKey() = encodedPublicKey

    override fun createPublicKeyAndRunPhaseOne(encoded: ByteArray) {
        exchangePartner.phaseOne(exchangePartner.createPublicKeyFromEncodedMaterial(encoded))
    }

    override fun generateSecret() = exchangePartner.generateSharedSecret().asHex()
}