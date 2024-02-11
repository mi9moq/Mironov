package com.mironov.tinkofftesttask.di.module

import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.NavigatorHolder
import com.github.terrakok.cicerone.Router
import com.mironov.tinkofftesttask.di.annotation.AppScope
import com.mironov.tinkofftesttask.navigation.router.ActivityRouter
import com.mironov.tinkofftesttask.navigation.router.ActivityRouterImpl
import com.mironov.tinkofftesttask.navigation.router.PopularRouter
import com.mironov.tinkofftesttask.navigation.router.PopularRouterImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface NavigationModule {

    companion object {

        private val cicerone: Cicerone<Router> = Cicerone.create()

        @AppScope
        @Provides
        fun provideRouter(): Router = cicerone.router

        @AppScope
        @Provides
        fun provideNavigatorHolder(): NavigatorHolder = cicerone.getNavigatorHolder()
    }

    @AppScope
    @Binds
    fun bindPopularRouter(impl: PopularRouterImpl): PopularRouter

    @AppScope
    @Binds
    fun bindActivityRouter(impl: ActivityRouterImpl): ActivityRouter
}