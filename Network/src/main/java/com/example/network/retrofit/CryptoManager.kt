package com.example.network.retrofit

import android.security.keystore.KeyProperties
import java.security.KeyStore
import java.security.MessageDigest
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

class CryptoManager {
    private var key: SecretKeySpec? = null

    fun encrypt(strToEncrypt: String, secret: String): Pair<ByteArray, ByteArray> {
        val plainText = strToEncrypt.toByteArray(Charsets.UTF_8)
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.ENCRYPT_MODE, key ?: generateKey(secret))
        val cipherText = cipher.doFinal(plainText)
        return cipherText to cipher.iv
    }

    fun decrypt(dataToDecrypt: ByteArray, secret: String, iv: ByteArray): ByteArray {
        val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
        cipher.init(Cipher.DECRYPT_MODE, key ?: generateKey(secret), IvParameterSpec(iv))
        val cipherText = cipher.doFinal(dataToDecrypt)
        return cipherText
    }

    private fun generateKey(password: String): SecretKeySpec {
        val digest: MessageDigest = MessageDigest.getInstance("SHA-256")
        val bytes = password.toByteArray()
        digest.update(bytes, 0, bytes.size)
        val key = digest.digest()
        val secretKeySpec = SecretKeySpec(key, "AES")
        return secretKeySpec
    }

    private val keyStore = KeyStore.getInstance("AndroidKeyStore").apply {
        load(null)
    }

    companion object {
        private const val ALGORITHM = KeyProperties.KEY_ALGORITHM_AES
        private const val BLOCK_MODE = KeyProperties.BLOCK_MODE_ECB
        private const val PADDING = KeyProperties.ENCRYPTION_PADDING_NONE
        private const val TRANSFORMATION = "$ALGORITHM/$BLOCK_MODE/$PADDING"
    }

}