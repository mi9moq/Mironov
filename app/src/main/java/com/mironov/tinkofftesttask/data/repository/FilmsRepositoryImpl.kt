package com.mironov.tinkofftesttask.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.mironov.tinkofftesttask.data.converter.DetailInfoConverter
import com.mironov.tinkofftesttask.data.converter.PopularFilmConverter
import com.mironov.tinkofftesttask.data.datasource.FilmLocalDataSource
import com.mironov.tinkofftesttask.data.datasource.FilmRemoteDataSource
import com.mironov.tinkofftesttask.di.annotation.IoDispatcher
import com.mironov.tinkofftesttask.domain.entity.FilmDetailInfo
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.domain.repository.FilmsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FilmsRepositoryImpl @Inject constructor(
    private val remoteDataSource: FilmRemoteDataSource,
    private val localDataSource: FilmLocalDataSource,
    private val popularFilmConverter: PopularFilmConverter,
    private val detailInfoConverter: DetailInfoConverter,
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : FilmsRepository {

    override fun getPopular(): Flow<PagingData<FilmInfo>> =
        remoteDataSource.getPopular().map {
            it.map(popularFilmConverter::dtoToEntity)
        }


    override suspend fun getById(id: Int): FilmDetailInfo = withContext(dispatcher) {
        remoteDataSource.getById(id).let(detailInfoConverter::convert)
    }

    override suspend fun addToFavourite(film: FilmInfo) = withContext(dispatcher) {
        localDataSource.addToFavourite(popularFilmConverter.entityToDbModel(film))
    }

    override fun getFavourite(): Flow<List<FilmInfo>> = localDataSource.getFavourite().map {
        it.map(popularFilmConverter::dbModelToEntity)
    }
}