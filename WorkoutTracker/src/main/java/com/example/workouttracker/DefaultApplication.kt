package com.example.workouttracker

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

class DefaultApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@DefaultApplication)
            modules(
                FeatureModule,
            )
        }
    }

    override fun onTerminate() {
        super.onTerminate()
        stopKoin()
    }
}