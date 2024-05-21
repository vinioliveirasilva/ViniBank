package com.vini.featuresignup

import com.vini.featuresignup.steps.accounttype.AccountTypeViewModel
import com.vini.featuresignup.steps.createpassword.CreatePasswordViewModel
import com.vini.featuresignup.steps.email.EmailViewModel
import com.vini.featuresignup.steps.personalinfo.PersonalInfoViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val SignUpModule = module {
    //scope<SignUpActivity> {
        viewModelOf(::SignUpViewModel)
        viewModelOf(::EmailViewModel)
        viewModelOf(::PersonalInfoViewModel)
        viewModelOf(::AccountTypeViewModel)
        viewModelOf(::CreatePasswordViewModel)
        factoryOf(::SignUpRepository)
    //}
}