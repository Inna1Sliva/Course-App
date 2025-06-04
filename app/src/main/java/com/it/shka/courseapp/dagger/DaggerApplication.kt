package com.it.shka.courseapp.dagger

import android.app.Application

class DaggerApplication(): Application() {
    lateinit var appComponent: ApplicationComponent
    override fun onCreate() {
        super.onCreate()
       appComponent = DaggerApplicationComponent
           .builder()
           .build()
    }
}