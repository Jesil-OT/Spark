package com.jesil.spark.core

import android.app.Application
import com.jesil.spark.core.di.coreModule
import com.jesil.spark.home.di.homeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class SparkApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        startKoin {
            androidContext(this@SparkApplication)
            modules(coreModule, homeModule)
        }
    }
}