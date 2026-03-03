package com.example.effectivemobiletest

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.example.effectivemobiletest.favorite.di.favoriteModule
import com.example.effectivemobiletest.main.di.mainModule
import com.example.effectivemobiletest.profile.di.profileModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        startKoin {
            androidContext(this@App)
            modules(mainModule)
            modules(favoriteModule)
            modules(profileModule)
        }
    }
}
