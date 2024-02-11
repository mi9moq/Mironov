package com.mironov.tinkofftesttask

import android.app.Application
import com.mironov.tinkofftesttask.di.component.DaggerAppComponent

class FilmsApp: Application() {

    val component by lazy {
        DaggerAppComponent.factory().create(this)
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}