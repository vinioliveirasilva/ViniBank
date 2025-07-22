package com.example.network

import com.example.network.retrofit.EncodeProvider
import com.vini.common.Benchmark
import java.security.KeyFactory
import java.security.PublicKey
import java.security.spec.X509EncodedKeySpec
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

class FastKeyExchangeManager(
    private val encoderProvider: EncodeProvider,
) {
    lateinit var secretKey: SecretKey
    lateinit var encryptedSecretKey: ByteArray
    lateinit var iv: ByteArray
    private val encodedPublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIs+H+vK+arQwl8OW+R7k2MObdrWrR5IxUKGBObtzj1qtbXQYnaoOGxC1XZg9PgVpL86VM5heyRSS0tFvBVl9PECAwEAAQ=="

    fun init() {
        Benchmark.bench("Cripto1") {
            val keyGenerator = KeyGenerator.getInstance("AES")
            keyGenerator.init(256) // AES-256

            //generate public key
            val decodedPublicKey = encoderProvider.decode(encodedPublicKey)
            val keyFactory = KeyFactory.getInstance("RSA")
            val publicKeySpec = X509EncodedKeySpec(decodedPublicKey)
            val publicKey = keyFactory.generatePublic(publicKeySpec)

            //generate secret key and must send to backend
            secretKey = keyGenerator.generateKey()
            val encodedSecretKey = encoderProvider.encode(secretKey.encoded)
            encryptedSecretKey = internalEncrypt(encodedSecretKey, publicKey)
        }
    }

    fun getEncodedPublicKey(): ByteArray = encryptedSecretKey

    fun encrypt(data: String, iv: ByteArray): Pair<ByteArray, ByteArray> {
        val plainText = data.toByteArray(Charsets.UTF_8)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, IvParameterSpec(iv))
        return cipher.doFinal(plainText) to cipher.iv
    }

    fun decrypt(encryptedData: ByteArray, iv: ByteArray): Pair<ByteArray, ByteArray> {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, secretKey, IvParameterSpec(iv))
        return cipher.doFinal(encryptedData) to cipher.iv
    }

    private fun internalEncrypt(data: String, publicKey: PublicKey): ByteArray {
        val cipher: Cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        return cipher.doFinal(data.toByteArray())
    }

    fun createPublicKeyAndRunPhaseOne(encoded: ByteArray) { iv = encoded }
}