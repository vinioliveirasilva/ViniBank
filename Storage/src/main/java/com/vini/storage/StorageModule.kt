package com.vini.storage

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val StorageModule = module {
    factoryOf(::SecureStorage)
    factoryOf(::LocalStorage)
}