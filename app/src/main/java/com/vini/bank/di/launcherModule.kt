package com.vini.bank.di

import com.vini.bank.LauncherActivity
import com.vini.bank.LauncherViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val LauncherModule = module {
    scope<LauncherActivity> {
        viewModelOf(::LauncherViewModel)
    }
}