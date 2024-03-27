package com.vini.featurelogin

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object LoginModule {
    val instance = module {
        viewModelOf(::LoginViewModel)
    }
}