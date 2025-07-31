package com.example.network

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.network.ktor.KtorHttpClientProvider
import com.example.network.ktor.KtorInitializer
import com.example.network.retrofit.EncodeProvider
import com.example.network.retrofit.FlowCallAdapterFactory
import com.example.network.retrofit.GitHubService
import com.example.network.retrofit.RetrofitInitializer
import com.example.network.retrofit.converter.jsonConverter.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
val NetworkModule = module {

    single<EncodeProvider> {
        EncodeProvider()
    }

    single<CryptoInterceptor> {
        CryptoInterceptor(encoderProvider = get(), keyExchangeManager = get())
    }
    single {
        AesCryptoInterceptor(encoderProvider = get(), keyExchangeManager = get())
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .addInterceptor(get<AesCryptoInterceptor>())
            .connectTimeout(Duration.ofSeconds(60))
            .readTimeout(Duration.ofSeconds(60))
            .writeTimeout(Duration.ofSeconds(60))
            .callTimeout(Duration.ofSeconds(60))
            .build()
    }

    val json = Json { ignoreUnknownKeys = true }

    single<Retrofit> {
        Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()) )
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .client(get<OkHttpClient>())
            .build()
    }

    factory<GitHubService> {
        get<Retrofit>().create(GitHubService::class.java)
    }

    factoryOf(::RetrofitInitializer)

    single {
        KtorHttpClientProvider(
            encoderProvider = get(),
            keyExchangeManager = get()
        )
    }

    single {
        KtorInitializer(
            provider = get()
        )
    }
}