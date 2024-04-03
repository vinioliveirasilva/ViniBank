package com.vini.common

import com.vini.common.gson.GsonProvider
import org.koin.dsl.module

val CommonModule = module {
    factory { GsonProvider() }
}