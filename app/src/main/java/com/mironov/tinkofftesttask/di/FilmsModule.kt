package com.mironov.tinkofftesttask.di

import com.mironov.tinkofftesttask.data.datasource.FilmRemoteDataSource
import com.mironov.tinkofftesttask.data.datasource.FilmRemoteDataSourceImpl
import com.mironov.tinkofftesttask.data.repository.FilmsRepositoryImpl
import com.mironov.tinkofftesttask.domain.repository.FilmsRepository
import dagger.Binds
import dagger.Module

@Module
interface FilmsModule {

    @AppScope
    @Binds
    fun bindRemoteDataSource(impl: FilmRemoteDataSourceImpl): FilmRemoteDataSource

    @AppScope
    @Binds
    fun bindRepository(impl: FilmsRepositoryImpl): FilmsRepository
}