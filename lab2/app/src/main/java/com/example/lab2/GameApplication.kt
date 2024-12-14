package com.example.lab2

import android.app.Application
import com.example.lab2.data.AppContainer
import com.example.lab2.data.AppDataContainer
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class GameApplication : Application() {

    /**
     * AppContainer instance used by the rest of classes to obtain dependencies
     */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}