package com.vini.bank.di

import com.vini.common.CommonModule
import com.vini.featurelogin.LoginModule
import com.vini.featuresignup.SignUpModule
import com.vini.storage.StorageModule
import org.koin.dsl.module

val FeatureModules = module {
    includes(
        LoginModule,
        LauncherModule,
        SignUpModule,
    )
}

val CoreModules = module {
    includes(
        StorageModule,
        CommonModule,
    )
}