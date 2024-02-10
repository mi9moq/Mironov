package com.mironov.tinkofftesttask.navigation.router

import com.github.terrakok.cicerone.Router
import com.mironov.tinkofftesttask.navigation.screen.getDetailsScreen
import javax.inject.Inject

interface PopularRouter {

    fun openDetails(id: Int)
}

class PopularRouterImpl @Inject constructor(
    private val router: Router
) : PopularRouter {

    override fun openDetails(id: Int) {
        router.navigateTo(getDetailsScreen(id))
    }
}