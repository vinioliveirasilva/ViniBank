package com.vini.featurehome

import com.vini.featurehome.contentprovider.CardContentProvider
import com.vini.featurehome.contentprovider.ContentProvider
import com.vini.featurehome.contentprovider.InvestmentContentProvider
import com.vini.featurehome.contentprovider.MainContentProvider
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val HomeModule = module {
    viewModelOf(::HomeViewModel)
    single { HomeContentProviderImpl(getAll()) }
    singleOf(::InvestmentContentProvider) bind ContentProvider::class
    singleOf(::CardContentProvider) bind ContentProvider::class
    singleOf(::MainContentProvider) bind ContentProvider::class
}