package com.vini.bank

import android.app.Application
import org.koin.core.context.startKoin

class DefaultApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {

        }
    }
}