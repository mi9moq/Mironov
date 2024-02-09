package com.mironov.tinkofftesttask.di

import dagger.Component

@AppScope
@Component(
    modules = [
        NetworkModule::class,
        FilmsModule::class,
        DispatcherModule::class,
    ]
)
interface AppComponent {
}