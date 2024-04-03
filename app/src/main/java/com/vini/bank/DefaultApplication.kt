package com.vini.bank

import android.app.Application
import com.vini.bank.di.CoreModules
import com.vini.bank.di.FeatureModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class DefaultApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DefaultApplication)
            modules(
                CoreModules,
                FeatureModules,
            )
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}