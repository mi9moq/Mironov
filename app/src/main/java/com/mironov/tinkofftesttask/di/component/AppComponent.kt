package com.mironov.tinkofftesttask.di.component

import android.app.Application
import com.mironov.tinkofftesttask.FilmsApp
import com.mironov.tinkofftesttask.ui.avtivity.MainActivity
import com.mironov.tinkofftesttask.di.annotation.AppScope
import com.mironov.tinkofftesttask.di.module.DataBaseModule
import com.mironov.tinkofftesttask.di.module.DispatcherModule
import com.mironov.tinkofftesttask.di.module.FilmsModule
import com.mironov.tinkofftesttask.di.module.NavigationModule
import com.mironov.tinkofftesttask.di.module.NetworkModule
import com.mironov.tinkofftesttask.di.module.ViewModelModule
import com.mironov.tinkofftesttask.ui.detail.DetailFragment
import com.mironov.tinkofftesttask.ui.favourite.FavouriteFragment
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

    fun inject(fragment: FavouriteFragment)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application
        ): AppComponent
    }
}