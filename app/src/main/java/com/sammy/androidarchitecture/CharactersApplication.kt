package com.sammy.androidarchitecture

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CharactersApplication : Application(){
    override fun onCreate() {
        super.onCreate()
        initTimber()
    }
    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}