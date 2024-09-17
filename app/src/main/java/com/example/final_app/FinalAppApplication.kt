package com.example.final_app

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class FinalAppApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            // declare Android context
            androidContext(this@FinalAppApplication)
            // declare modules
            modules(appModule)
        }
    }
}
