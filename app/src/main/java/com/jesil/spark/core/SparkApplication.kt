package com.jesil.spark.core

import android.app.Application
import timber.log.Timber

class SparkApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}