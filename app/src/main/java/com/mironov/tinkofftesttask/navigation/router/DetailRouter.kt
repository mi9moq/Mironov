package com.mironov.tinkofftesttask.navigation.router

import com.github.terrakok.cicerone.Router
import javax.inject.Inject

interface DetailRouter {

    fun back()
}

class DetailRouterImpl @Inject constructor(
    private val router: Router
): DetailRouter{

    override fun back() {
        router.exit()
    }
}