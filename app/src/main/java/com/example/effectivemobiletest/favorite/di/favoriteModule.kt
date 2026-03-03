package com.example.effectivemobiletest.favorite.di

import com.example.effectivemobiletest.favorite.presentation.FavoriteViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val favoriteModule = module {
    viewModelOf(::FavoriteViewModel)
}