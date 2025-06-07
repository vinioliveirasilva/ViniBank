package com.vini.storage

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val StorageModule = module {
    factoryOf(::SecureStorage)
    factory { CryptographyProvider() }
    factoryOf(::LocalStorage)
    factoryOf(::SessionStorage)
}