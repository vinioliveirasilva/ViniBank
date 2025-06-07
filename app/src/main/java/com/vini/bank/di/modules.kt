package com.vini.bank.di

import com.example.auth.AuthModule
import com.example.network.NetworkModule
import com.example.router.RouterModule
import com.example.serverdriveui.di.ServerDriveUiModule
import com.vini.common.CommonModule
import com.vini.featurehome.HomeModule
import com.vini.featurelogin.LoginModule
import com.vini.featuresignup.SignUpModule
import com.vini.storage.StorageModule
import org.koin.dsl.module

val FeatureModules = module {
    includes(
        LoginModule,
        LauncherModule,
        SignUpModule,
        HomeModule,
        ServerDriveUiModule,
    )
}

val CoreModules = module {
    includes(
        StorageModule,
        CommonModule,
        AuthModule,
        RouterModule,
        NetworkModule,
    )
}