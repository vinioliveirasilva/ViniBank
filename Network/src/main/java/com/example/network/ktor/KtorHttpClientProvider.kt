package com.example.network.ktor

import com.example.network.AesCryptoInterceptor
import com.example.network.FastKeyExchangeManager
import com.example.network.retrofit.EncodeProvider
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.request.request
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KtorHttpClientProvider(
    private val encoderProvider: EncodeProvider,
    private val keyExchangeManager: FastKeyExchangeManager,
    private val client: HttpClient = HttpClient(Android) {
        install(Logging) {
            logger = Logger.ANDROID
            level = LogLevel.BODY
            //sanitizeHeader { header -> header == HttpHeaders.Authorization }
        }
    },
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO),
) {

    private lateinit var sessionId: String
    private lateinit var iv: String

    suspend fun startHandShake() =
        client.request("$BASE_URL/initialize") {
            method = HttpMethod.Companion.Get
            contentType(ContentType.Text.Plain)
            header(
                AesCryptoInterceptor.Companion.Header.PUBLIC_KEY,
                encoderProvider.encode(keyExchangeManager.getEncodedPublicKey())
            )
        }.doOnHandShakeResponse()

    private suspend fun HttpResponse.doOnHandShakeResponse() {
        sessionId = headers[AesCryptoInterceptor.Companion.Header.SESSION_ID].orEmpty()
        keyExchangeManager.createPublicKeyAndRunPhaseOne(encoderProvider.decode(body<String>()))
    }

    @OptIn(InternalAPI::class)
    fun request(path: String, method: HttpMethod, body: String, onSuccess: suspend (String) -> Unit) = scope.launch {
        val (encryptedData, lastIv) = keyExchangeManager.encrypt(
            body,
            keyExchangeManager.iv
        )
        client.request("$BASE_URL$path") {
            this.method = method
            contentType(ContentType.Text.Plain)
            header(AesCryptoInterceptor.Companion.Header.IV, encoderProvider.encode(lastIv))
            header(AesCryptoInterceptor.Companion.Header.SESSION_ID, sessionId)
            this.body = encoderProvider.encode(encryptedData)
        }.doOnEncryptedResponse(onSuccess)
    }

    private suspend fun HttpResponse.doOnEncryptedResponse(onSuccess: suspend (String) -> Unit) {
        iv = headers[AesCryptoInterceptor.Companion.Header.IV] ?: throw IllegalStateException("IV not found")
        val dec = keyExchangeManager.decrypt(
            encryptedData = encoderProvider.decode(body()),
            iv = encoderProvider.decode(iv)
        )
        onSuccess(dec.decodeToString())
    }

    private companion object {
        const val BASE_URL = "http://10.0.2.2:8080"
    }
}