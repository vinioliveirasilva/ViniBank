package com.vini.bank.di

import com.vini.featurelogin.LoginModule
import org.koin.dsl.module

val FeatureModules = module {
    includes(
        LoginModule.instance,
    )
}