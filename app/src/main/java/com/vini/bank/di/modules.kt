package com.vini.bank.di

import com.example.network.NetworkModule
import com.example.router.RouterModule
import com.example.serverdriveui.di.ServerDriverUiModules
import com.vini.common.CommonModule
import com.vini.storage.StorageModule
import org.koin.dsl.module

val FeatureModules = module {
    includes(
        LauncherModule,
        ServerDriverUiModules,
    )
}

val CoreModules = module {
    includes(
        StorageModule,
        CommonModule,
        RouterModule,
        NetworkModule,
    )
}