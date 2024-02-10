package com.mironov.tinkofftesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.mironov.tinkofftesttask.di.AppComponent
import com.mironov.tinkofftesttask.di.DaggerAppComponent
import com.mironov.tinkofftesttask.presentation.ViewModelFactory
import com.mironov.tinkofftesttask.presentation.activity.ActivityViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val component: AppComponent by lazy {
        (application as FilmsApp).component
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = AppNavigator(this, R.id.fragment_container)

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel by lazy {
        ViewModelProvider(this, viewModelFactory)[ActivityViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null){
            viewModel.openPopular()
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}