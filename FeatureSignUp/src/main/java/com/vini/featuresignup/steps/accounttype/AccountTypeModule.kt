package com.vini.featuresignup.steps.accounttype

import com.vini.featuresignup.steps.createpassword.CreatePasswordFragment
import com.vini.featuresignup.steps.createpassword.CreatePasswordViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val AccountTypeModule = module {
    scope<CreatePasswordFragment> {
        viewModelOf(::CreatePasswordViewModel)
    }
}