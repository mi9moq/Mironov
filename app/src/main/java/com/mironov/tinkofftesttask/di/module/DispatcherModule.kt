package com.mironov.tinkofftesttask.di.module

import com.mironov.tinkofftesttask.di.annotation.AppScope
import com.mironov.tinkofftesttask.di.annotation.IoDispatcher
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
object DispatcherModule {

    @AppScope
    @Provides
    @IoDispatcher
    fun provideIoDispatcher(): CoroutineDispatcher = Dispatchers.IO
}