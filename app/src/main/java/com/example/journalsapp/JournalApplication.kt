package com.example.journalsapp

import android.app.Application
import com.example.journalsapp.data.AppContainer
import com.example.journalsapp.data.DefaultAppContainer

class JournalApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}