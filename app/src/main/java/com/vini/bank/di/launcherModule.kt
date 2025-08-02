package com.vini.bank.di

import com.example.network.FastKeyExchangeManager
import com.vini.bank.LauncherActivity
import com.vini.bank.LauncherViewModel
import com.vini.bank.initializers.CryptoBindInitializer
import com.vini.bank.initializers.CryptoKeyInitializer
import com.vini.bank.initializers.Initializer
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val LauncherModule = module {
    scope<LauncherActivity> {
        viewModel { LauncherViewModel(get(), getAll()) }
        //viewModelOf(::LauncherViewModel)
    }
    singleOf(::CryptoKeyInitializer) bind Initializer::class
    singleOf(::CryptoBindInitializer) bind Initializer::class
    singleOf(::FastKeyExchangeManager)
}