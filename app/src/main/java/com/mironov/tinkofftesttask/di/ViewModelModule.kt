package com.mironov.tinkofftesttask.di

import androidx.lifecycle.ViewModel
import com.mironov.tinkofftesttask.presentation.popular.PopularViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(PopularViewModel::class)
    fun bindPopularViewModel(impl: PopularViewModel): ViewModel
}