package com.mironov.tinkofftesttask.ui.avtivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.mironov.tinkofftesttask.FilmsApp
import com.mironov.tinkofftesttask.R
import com.mironov.tinkofftesttask.databinding.ActivityMainBinding
import com.mironov.tinkofftesttask.di.component.AppComponent
import com.mironov.tinkofftesttask.presentation.ViewModelFactory
import com.mironov.tinkofftesttask.presentation.activity.ActivityViewModel
import com.mironov.tinkofftesttask.ui.detail.DetailFragment
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
        setupBackStackChangedListener()
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

    private fun changeEnableButtons() {
        with(binding) {
            favourite.isEnabled = !favourite.isEnabled
            popular.isEnabled = !popular.isEnabled
        }
    }

    private fun setupBackStackChangedListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

            if (currentFragment is DetailFragment) {
                binding.favourite.isVisible = false
                binding.popular.isVisible = false
            } else {
                binding.favourite.isVisible = true
                binding.popular.isVisible = true
            }
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