package com.example.network

import com.example.network.retrofit.CryptoManager
import com.example.network.retrofit.DHExchangePartner
import com.example.network.retrofit.EncodeProvider
import com.example.network.retrofit.asHex
import okhttp3.Interceptor
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Invocation
import java.security.AlgorithmParameterGenerator
import java.security.KeyPair
import java.security.SecureRandom
import javax.crypto.spec.DHParameterSpec

annotation class Encrypt()
annotation class HandShake()

class KeyExchangeManager() {
    lateinit var exchangePartner: DHExchangePartner
    lateinit var keyPair: KeyPair
    lateinit var encodedPublicKey: ByteArray

    fun init() {
        AlgorithmParameterGenerator.getInstance("DH").apply {
            init(512, SecureRandom())
            exchangePartner = DHExchangePartner(
                this.generateParameters()
                    .getParameterSpec<DHParameterSpec?>(DHParameterSpec::class.java) as DHParameterSpec
            )
            keyPair = exchangePartner.createPersonalDHKeypairAndInitAgreement()
            encodedPublicKey = keyPair.public.encoded
        }
    }
}

class CryptoInterceptor(
    private val encoderProvider: EncodeProvider,
    private val keyExchangeManager: KeyExchangeManager,
    private val crypto: CryptoManager = CryptoManager()
) : Interceptor {

    private lateinit var sessionId: String
    private lateinit var iv: String
    private lateinit var secret: String

    fun <T : Annotation> Request.getAnnotation(annotationClass: Class<T>): T? {
        return tag(Invocation::class.java)?.method()?.getAnnotation(annotationClass)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()

        val isEncrypt = req.getAnnotation(Encrypt::class.java) != null
        val isHandShake = req.getAnnotation(HandShake::class.java) != null

        return chain.proceed(req.handleRequest(isHandShake, isEncrypt)).handleResponse(isHandShake, isEncrypt)
    }

    private fun Request.handleRequest(isHandShake: Boolean, isEncrypt: Boolean): Request {
        return when {
            isHandShake -> createHandShakeRequest()
            isEncrypt -> createEncryptedRequest()
            else -> this
        }
    }

    private fun Request.createHandShakeRequest(): Request = newBuilder().addHandShakeHeaders().build()

    private fun Request.createEncryptedRequest(): Request {
        val (data, iv) = crypto.encrypt(readBody(this), secret)
        return newBuilder()
            .addEncryptHeaders(iv, sessionId)
            .post(
                RequestBody.create(
                    TEXT_MEDIA_TYPE,
                    encoderProvider.encode(data)
                )
            ).build()
    }

    private fun Request.Builder.addEncryptHeaders(iv: ByteArray, sessionId: String) = apply {
        header(Header.IV, encoderProvider.encode(iv))
        header(Header.SESSION_ID, sessionId)
    }

    private fun Request.Builder.addHandShakeHeaders() = apply {
        header(Header.PUBLIC_KEY, encoderProvider.encode(keyExchangeManager.encodedPublicKey))
    }

    private fun Response.handleResponse(isHandShake: Boolean, isEncrypt: Boolean): Response {
        return when {
            isHandShake -> doOnHandShakeResponse()
            isEncrypt -> doOnEncryptedResponse()
            else -> this
        }
    }

    private fun Response.doOnHandShakeResponse(): Response {
        sessionId = header(Header.SESSION_ID).orEmpty()

        val partnerPublicKey = keyExchangeManager.exchangePartner.createPublicKeyFromEncodedMaterial(
            encoderProvider.decode(readBody(this))
        )
        keyExchangeManager.exchangePartner.phaseOne(partnerPublicKey)

        secret = keyExchangeManager.exchangePartner.generateSharedSecret().asHex()
        return this.newBuilder().body(ResponseBody.create(JSON_MEDIA_TYPE, EMPTY_JSON)).build()
    }

    private fun Response.doOnEncryptedResponse(): Response {
        iv = header(Header.IV) ?: return this
        val dec = crypto.decrypt(
            dataToDecrypt = encoderProvider.decode(readBody(this)),
            secret = secret,
            iv = encoderProvider.decode(iv)
        ).decodeToString()
        return newBuilder()
            .body(ResponseBody.create(JSON_MEDIA_TYPE, dec))
            .build()
    }

    private fun readBody(result: Response): String? {
        val responseBody = result.body()
        val source = responseBody?.source()
        source?.request(Long.MAX_VALUE)
        val buffer = source?.buffer

        return buffer?.clone()?.readUtf8()
    }

    private fun readBody(request: Request): String {
        val newReq = request.newBuilder().build()
        val buffer = Buffer()
        newReq.body()?.writeTo(buffer)

        return buffer.readUtf8().orEmpty()
    }

    private companion object {
        val JSON_MEDIA_TYPE = MediaType.parse("application/json")
        val TEXT_MEDIA_TYPE = MediaType.parse("text/plain")
        const val EMPTY_JSON = "{}"

        object Header {
            const val IV = "iv"
            const val SESSION_ID = "sessionId"
            const val PUBLIC_KEY = "publicKey"
        }
    }
}