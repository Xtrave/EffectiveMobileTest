package com.example.effectivemobiletest.di

import com.example.effectivemobiletest.authorization.navigation.LoginNavigation
import com.example.effectivemobiletest.navigation.AppNavigation
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val navigationModule = module {
    factory<LoginNavigation> { AppNavigation() }
}