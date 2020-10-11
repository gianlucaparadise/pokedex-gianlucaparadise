package com.gianlucaparadise.pokedex

import android.app.Application
import com.gianlucaparadise.pokedex.di.appModule
import com.gianlucaparadise.pokedex.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MainApplication)
            modules(appModule, networkModule)
        }
    }
}