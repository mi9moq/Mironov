package com.mironov.tinkofftesttask.presentation.activity

import androidx.lifecycle.ViewModel
import com.mironov.tinkofftesttask.navigation.router.ActivityRouter
import javax.inject.Inject

class ActivityViewModel @Inject constructor(
    private val router: ActivityRouter
) : ViewModel() {

    fun openPopular() {
        router.openPopular()
    }

    fun openFavourite() {
        router.openFavourite()
    }

    fun back() {
        router.popBackStack()
    }
}