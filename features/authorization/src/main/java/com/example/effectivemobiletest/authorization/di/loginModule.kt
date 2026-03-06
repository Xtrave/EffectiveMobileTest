package com.example.effectivemobiletest.authorization.di

import com.example.effectivemobiletest.authorization.presentation.LoginViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val loginModule = module {
    viewModelOf(::LoginViewModel)
}