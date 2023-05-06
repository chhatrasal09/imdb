package com.app.imdb

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class IMDbApp : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}