package com.example.router

import androidx.activity.ComponentActivity
import org.koin.dsl.module

val RouterModule = module {
    factory { (context: ComponentActivity) ->  FeatureRouter(context) }
}