package com.example.effectivemobiletest.main.di

import com.example.effectivemobiletest.main.data.remote.CoursesApi
import com.example.effectivemobiletest.main.data.remote.MockInterceptor
import com.example.effectivemobiletest.main.presentation.MainViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val mainModule = module {

    viewModelOf(::MainViewModel)

    single { MockInterceptor(androidContext()) }

    single {
        OkHttpClient.Builder()
            .addInterceptor(get<MockInterceptor>())
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://example.com/") // важно чтобы был /
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    single { get<Retrofit>().create(CoursesApi::class.java) }
}