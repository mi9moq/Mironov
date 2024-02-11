package com.mironov.tinkofftesttask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.mironov.tinkofftesttask.databinding.ActivityMainBinding
import com.mironov.tinkofftesttask.di.AppComponent
import com.mironov.tinkofftesttask.presentation.ViewModelFactory
import com.mironov.tinkofftesttask.presentation.activity.ActivityViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    val component: AppComponent by lazy {
        (application as FilmsApp).component
    }

    private lateinit var binding: ActivityMainBinding

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
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            viewModel.openPopular()
        }
        addClickListeners()
    }

    private fun addClickListeners() {
        with(binding) {
            favourite.setOnClickListener {
                viewModel.openFavourite()
                changeEnableButtons()
            }

            popular.setOnClickListener {
                viewModel.back()
                changeEnableButtons()
            }
        }
    }

    private fun changeEnableButtons(){
        with(binding){
            favourite.isEnabled = !favourite.isEnabled
            popular.isEnabled = !popular.isEnabled
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