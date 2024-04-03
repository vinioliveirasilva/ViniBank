package com.vini.featuresignup

import com.vini.featuresignup.steps.accounttype.AccountTypeModule
import com.vini.featuresignup.steps.createpassword.CreatePasswordModule
import com.vini.featuresignup.steps.email.EmailModule
import com.vini.featuresignup.steps.personalinfo.PersonalInfoModule
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val SignUpModule = module {
    scope<SignUpActivity> {
        viewModelOf(::SignUpViewModel)
    }
    includes(
        EmailModule,
        PersonalInfoModule,
        AccountTypeModule,
        CreatePasswordModule,
    )
}