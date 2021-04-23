package com.bosch.smartshopping.di

import com.bosch.smartshopping.repositories.LocalRepository
import com.bosch.smartshopping.repositories.NetworkRepository
import com.bosch.smartshopping.viewmodel.WelcomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import kotlin.reflect.KClass

val demoAppModule = module{
    viewModel { WelcomeViewModel<KClass<Any>>(localRepository = get(), networkRepository = get()) }
    single {
        LocalRepository()
    }
    single {
        NetworkRepository()
    }
}