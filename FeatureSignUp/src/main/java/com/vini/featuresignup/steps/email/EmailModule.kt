package com.vini.featuresignup.steps.email

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val EmailModule = module {
    scope<EmailFragment> {
        viewModelOf(::EmailViewModel)
    }
}