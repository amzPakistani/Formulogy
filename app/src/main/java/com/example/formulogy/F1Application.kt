package com.example.formulogy

import android.app.Application
import com.example.formulogy.data.AppContainer
import com.example.formulogy.data.DefaultAppContainer

class F1Application:Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}