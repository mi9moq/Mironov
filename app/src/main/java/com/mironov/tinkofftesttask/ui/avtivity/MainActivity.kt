package com.mironov.tinkofftesttask.ui.avtivity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.mironov.tinkofftesttask.FilmsApp
import com.mironov.tinkofftesttask.R
import com.mironov.tinkofftesttask.databinding.ActivityMainBinding
import com.mironov.tinkofftesttask.di.component.AppComponent
import com.mironov.tinkofftesttask.presentation.ViewModelFactory
import com.mironov.tinkofftesttask.presentation.activity.ActivityState
import com.mironov.tinkofftesttask.presentation.activity.ActivityViewModel
import com.mironov.tinkofftesttask.ui.detail.DetailFragment
import com.mironov.tinkofftesttask.ui.utils.OnBackPressedListener
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity(), OnBackPressedListener {

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
        observeState()
        setupBackStackChangedListener()
        addClickListeners()
    }

    private fun observeState(){
        lifecycleScope.launch {
            viewModel.state.collect(::applyState)
        }
    }

    private fun applyState(state: ActivityState) {
        with(binding) {
            popular.isEnabled = state.popularEnable
            popular.isVisible = state.popularVisibility
            favourite.isEnabled = state.favouriteEnable
            favourite.isVisible = state.favouriteVisibility
        }
    }
    private fun addClickListeners() {
        with(binding) {
            favourite.setOnClickListener {
                viewModel.openFavourite()
            }

            popular.setOnClickListener {
                viewModel.back()
            }
        }
    }

    private fun setupBackStackChangedListener() {
        supportFragmentManager.addOnBackStackChangedListener {
            val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)

            if (currentFragment is DetailFragment) {
                viewModel.hideButtons()
            } else {
                viewModel.displayButtons()
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

    override fun onBackPressedListener() {
        viewModel.back()
    }
}