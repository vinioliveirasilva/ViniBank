package com.example.network

import com.example.network.retrofit.EncodeProvider
import com.example.network.retrofit.FlowCallAdapterFactory
import com.example.network.retrofit.GitHubService
import com.example.network.retrofit.RetrofitInitializer
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

val NetworkModule = module {

    single<EncodeProvider> {
        EncodeProvider()
    }

    single<CryptoInterceptor> {
        CryptoInterceptor(encoderProvider = get(), keyExchangeManager = get())
    }

    single<OkHttpClient> {
        OkHttpClient.Builder()
            .addInterceptor(get<CryptoInterceptor>())
            .build()
    }

    single<Retrofit> {
        Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .addCallAdapterFactory(FlowCallAdapterFactory())
            .client(get<OkHttpClient>())
            .build()
    }

    factory<GitHubService> {
        get<Retrofit>().create(GitHubService::class.java)
    }

    factoryOf(::RetrofitInitializer)
}