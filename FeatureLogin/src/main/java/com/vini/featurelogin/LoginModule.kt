package com.vini.featurelogin

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val LoginModule = module {
    viewModelOf(::LoginViewModel)
    factoryOf(::LoginRepository)
}