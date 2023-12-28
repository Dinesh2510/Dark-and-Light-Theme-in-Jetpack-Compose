package com.example.jetpackcomposedemo.application

import android.app.Application
import com.example.jetpackcomposedemo.utlis.Prefs

val prefs: Prefs by lazy {
    ThisApp.prefs!!
}

class ThisApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        prefs = Prefs(applicationContext)
    }

    companion object {
        var prefs: Prefs? = null
        lateinit var instance: ThisApp
            private set
    }
}