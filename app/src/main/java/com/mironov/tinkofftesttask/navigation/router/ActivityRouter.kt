package com.mironov.tinkofftesttask.navigation.router

import com.github.terrakok.cicerone.Router
import com.mironov.tinkofftesttask.navigation.screen.getFavourite
import com.mironov.tinkofftesttask.navigation.screen.getPopular
import javax.inject.Inject

interface ActivityRouter {

    fun openFavourite()

    fun openPopular()

    fun popBackStack()
}

class ActivityRouterImpl @Inject constructor(
    private val router: Router
) : ActivityRouter {

    override fun openFavourite() {
        router.navigateTo(getFavourite())
    }

    override fun openPopular() {
        router.newRootScreen(getPopular())
    }

    override fun popBackStack() {
        router.exit()
    }
}