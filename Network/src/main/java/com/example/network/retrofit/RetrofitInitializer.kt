package com.example.network.retrofit


import com.example.network.Encrypt
import com.example.network.HandShake
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

class RetrofitInitializer(
    val service: GitHubService,
) {

    fun abc() {
        CoroutineScope(Dispatchers.IO).launch {
            service.handShake()
                .catch { println(it) }
                .collect { }

            service.handShake2(toDecrypt = "SALVE QUEBRADA")
                .catch { }
                //.collect { }
        }
    }
}

interface GitHubService {
    @HandShake
    @GET("/initialize")
    fun handShake(): Flow<Any>

    @Encrypt
    @POST("/change-keys")
    fun handShake2(@Body toDecrypt: String): Flow<Any>
}
