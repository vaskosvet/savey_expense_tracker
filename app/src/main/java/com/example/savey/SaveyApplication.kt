package com.example.savey

import android.app.Application
import com.example.savey.data.AppContainer
import com.example.savey.data.AppDataContainer

class SaveyApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}