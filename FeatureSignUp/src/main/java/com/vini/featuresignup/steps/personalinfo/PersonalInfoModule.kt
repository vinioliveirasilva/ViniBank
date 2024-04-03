package com.vini.featuresignup.steps.personalinfo

import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val PersonalInfoModule = module {
    scope<PersonalInfoFragment> {
        viewModelOf(::PersonalInfoViewModel)
    }
}