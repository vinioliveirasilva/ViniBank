package com.vini.featuresignup.steps.createpassword

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val CreatePasswordModule = module {
    scope<CreatePasswordFragment> {
        viewModelOf(::CreatePasswordViewModel)
    }
}