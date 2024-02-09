package com.mironov.tinkofftesttask.di

import com.mironov.tinkofftesttask.MainActivity
import com.mironov.tinkofftesttask.ui.popular.PopularFragment
import dagger.Component

@AppScope
@Component(
    modules = [
        NetworkModule::class,
        FilmsModule::class,
        DispatcherModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent {

    fun inject(activity: MainActivity)

    fun inject(fragment: PopularFragment)
}