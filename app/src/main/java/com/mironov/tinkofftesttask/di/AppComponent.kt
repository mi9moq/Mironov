package com.mironov.tinkofftesttask.di

import android.app.Application
import com.mironov.tinkofftesttask.FilmsApp
import com.mironov.tinkofftesttask.MainActivity
import com.mironov.tinkofftesttask.ui.detail.DetailFragment
import com.mironov.tinkofftesttask.ui.popular.PopularFragment
import dagger.BindsInstance
import dagger.Component

@AppScope
@Component(
    modules = [
        NetworkModule::class,
        FilmsModule::class,
        DispatcherModule::class,
        ViewModelModule::class,
        NavigationModule::class,
        DataBaseModule::class,
    ]
)
interface AppComponent {

    fun inject(application: FilmsApp)

    fun inject(activity: MainActivity)

    fun inject(fragment: PopularFragment)

    fun inject(fragment: DetailFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}