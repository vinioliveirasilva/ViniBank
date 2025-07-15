package com.vini.common

import com.vini.common.json.JsonProvider
import org.koin.dsl.module

val CommonModule = module {
    factory { JsonProvider() }
}