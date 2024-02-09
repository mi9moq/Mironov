package com.mironov.tinkofftesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mironov.tinkofftesttask.di.AppComponent
import com.mironov.tinkofftesttask.di.DaggerAppComponent

class MainActivity : AppCompatActivity() {

    val component: AppComponent by lazy {
        DaggerAppComponent.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}