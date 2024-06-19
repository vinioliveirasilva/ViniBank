package com.vini.storage

import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.KeyStore
import java.security.MessageDigest
import java.security.PrivateKey
import javax.crypto.Cipher

class CryptographyProvider(
    private val messageDigest: MessageDigest = MessageDigest.getInstance(HASHING_ALGORITHM),
    private val keyStore: KeyStore = KeyStore.getInstance(PROVIDER_NAME),
    private val generator: KeyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM, PROVIDER_NAME),
) {

    private val encryptionCipher: Cipher = Cipher.getInstance(CIPHER_TYPE)
    private val decryptionCipher: Cipher = Cipher.getInstance(CIPHER_TYPE)

    init {
        keyStore.load(null)
        initGeneratorWithKeyGenParameterSpec(generator)
        getAndroidKeyStoreAsymmetricKeyPair().also {
            encryptionCipher.init(Cipher.ENCRYPT_MODE, it.public)
            decryptionCipher.init(Cipher.DECRYPT_MODE, it.private)
        }
    }

    private fun getAndroidKeyStoreAsymmetricKeyPair(): KeyPair {
        val privateKey = keyStore.getKey(KEY_ALIAS, null) as PrivateKey?
        val publicKey = keyStore.getCertificate(KEY_ALIAS)?.publicKey

        return if (privateKey != null && publicKey != null) {
            KeyPair(publicKey, privateKey)
        } else {
            generator.generateKeyPair()
        }
    }

    fun removeAndroidKeyStoreKey(alias: String) = keyStore.deleteEntry(alias)

    private fun initGeneratorWithKeyGenParameterSpec(generator: KeyPairGenerator) {
        val builder = KeyGenParameterSpec.Builder(
            KEY_ALIAS,
            KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT
        )
            .setBlockModes(KeyProperties.BLOCK_MODE_ECB)
            .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1)
        generator.initialize(builder.build())
    }

    fun encrypt(data: String): String {
        val bytes = encryptionCipher.doFinal(data.toByteArray())
        return Base64.encodeToString(bytes, Base64.DEFAULT)
    }

    fun decrypt(data: String): String {
        val encryptedData = Base64.decode(data, Base64.DEFAULT)
        val decodedData = decryptionCipher.doFinal(encryptedData)
        return String(decodedData)
    }

    fun hash(data: String): String {
        val hashedData = messageDigest.digest(data.toByteArray())
        return Base64.encodeToString(hashedData, Base64.DEFAULT)
    }

    private companion object {
        const val PROVIDER_NAME = "AndroidKeyStore"
        const val KEY_ALIAS = "CryptographyProvider.KeyAlias"
        const val PADDING = KeyProperties.ENCRYPTION_PADDING_RSA_PKCS1
        const val BLOCK_MODE = KeyProperties.BLOCK_MODE_ECB
        const val ALGORITHM = KeyProperties.KEY_ALGORITHM_RSA
        const val CIPHER_TYPE = "$ALGORITHM/$BLOCK_MODE/$PADDING"
        const val HASHING_ALGORITHM = KeyProperties.DIGEST_SHA256
    }
}