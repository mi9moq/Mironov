package com.mironov.tinkofftesttask.di.module

import com.mironov.tinkofftesttask.data.datasource.FilmLocalDataSource
import com.mironov.tinkofftesttask.data.datasource.FilmLocalDataSourceImpl
import com.mironov.tinkofftesttask.data.datasource.FilmRemoteDataSource
import com.mironov.tinkofftesttask.data.datasource.FilmRemoteDataSourceImpl
import com.mironov.tinkofftesttask.data.repository.FilmsRepositoryImpl
import com.mironov.tinkofftesttask.di.annotation.AppScope
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
    fun bindFilmLocalDataSource(impl: FilmLocalDataSourceImpl): FilmLocalDataSource

    @AppScope
    @Binds
    fun bindRepository(impl: FilmsRepositoryImpl): FilmsRepository
}