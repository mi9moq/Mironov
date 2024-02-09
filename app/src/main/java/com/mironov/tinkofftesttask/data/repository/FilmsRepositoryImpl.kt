package com.mironov.tinkofftesttask.data.repository

import com.mironov.tinkofftesttask.data.converter.PopularFilmConverter
import com.mironov.tinkofftesttask.data.datasource.FilmRemoteDataSource
import com.mironov.tinkofftesttask.di.IoDispatcher
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.domain.repository.FilmsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val remoteDataSource: FilmRemoteDataSource,
    private val popularFilmConverter: PopularFilmConverter,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : FilmsRepository {

    override suspend fun getPopular(): List<FilmInfo> = withContext(dispatcher) {
        remoteDataSource.getPopular().map(popularFilmConverter::convert)
    }

    override suspend fun getById(id: Int) {
        TODO("Not yet implemented")
    }
}