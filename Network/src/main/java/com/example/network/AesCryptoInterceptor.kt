package com.example.network

import com.example.network.retrofit.EncodeProvider
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okio.Buffer
import retrofit2.Invocation


class AesCryptoInterceptor(
    private val encoderProvider: EncodeProvider,
    private val keyExchangeManager: FastKeyExchangeManager,
) : Interceptor {

    private lateinit var sessionId: String
    private lateinit var iv: String

    fun <T : Annotation> Request.getAnnotation(annotationClass: Class<T>): T? {
        return tag(Invocation::class.java)?.method()?.getAnnotation(annotationClass)
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val req = chain.request()

        val isEncrypt = req.getAnnotation(Encrypt::class.java) != null
        val isHandShake = req.getAnnotation(HandShake::class.java) != null

        return chain.proceed(req.handleRequest(isHandShake, isEncrypt))
            .handleResponse(isHandShake, isEncrypt)
    }

    private fun Request.handleRequest(isHandShake: Boolean, isEncrypt: Boolean): Request {
        return when {
            isHandShake -> createHandShakeRequest()
            isEncrypt -> createEncryptedRequest()
            else -> this
        }
    }

    private fun Request.createHandShakeRequest(): Request =
        newBuilder().addHandShakeHeaders().build()

    private fun Request.createEncryptedRequest(): Request {
        val (data, iv) = keyExchangeManager.encrypt(readBody(this), keyExchangeManager.iv)
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
        header(Header.PUBLIC_KEY, encoderProvider.encode(keyExchangeManager.getEncodedPublicKey()))
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
        keyExchangeManager.createPublicKeyAndRunPhaseOne(encoderProvider.decode(readBody(this)))
        return this.newBuilder().body(ResponseBody.create(JSON_MEDIA_TYPE, EMPTY_JSON)).build()
    }

    private fun Response.doOnEncryptedResponse(): Response {
        iv = header(Header.IV) ?: return this
        val dec = keyExchangeManager.decrypt(
            encryptedData = encoderProvider.decode(readBody(this)),
            iv = encoderProvider.decode(iv)
        )
        return newBuilder()
            .body(ResponseBody.create(JSON_MEDIA_TYPE, dec.decodeToString()))
            .build()
    }

    private fun readBody(result: Response): String? {
        val responseBody = result.body
        val source = responseBody?.source()
        source?.request(Long.MAX_VALUE)
        val buffer = source?.buffer

        return buffer?.clone()?.readUtf8()
    }

    private fun readBody(request: Request): String {
        val newReq = request.newBuilder().build()
        val buffer = Buffer()
        newReq.body?.writeTo(buffer)

        return buffer.readUtf8()
    }

    companion object {
        val JSON_MEDIA_TYPE = "application/json".toMediaTypeOrNull()
        val TEXT_MEDIA_TYPE = "text/plain".toMediaTypeOrNull()
        const val EMPTY_JSON = "{}"

        object Header {
            const val IV = "iv"
            const val SESSION_ID = "sessionId"
            const val PUBLIC_KEY = "publicKey"
        }
    }
}