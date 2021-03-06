package com.example.photosapp

import android.app.Application
import com.example.photosapp.di.appModule
import com.example.photosapp.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@MyApplication)
            modules(listOf(appModule, dataModule))
        }

    }
}