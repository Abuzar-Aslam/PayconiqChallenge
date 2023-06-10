package com.example.payconiqchallenge.base

import android.app.Application
import com.example.payconiqchallenge.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Base Application class which will initialize the Koin on App is started . it'll be called even before any activity or service is called
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        // Initialize Koin
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }
}