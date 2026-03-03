package com.example.effectivemobiletest.main.di

import com.example.effectivemobiletest.main.presentation.MainViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val mainModule = module {
    viewModelOf(::MainViewModel)
}