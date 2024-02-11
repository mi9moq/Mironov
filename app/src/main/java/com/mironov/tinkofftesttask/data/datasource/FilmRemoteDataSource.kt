package com.mironov.tinkofftesttask.data.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mironov.tinkofftesttask.data.remote.api.FilmApi
import com.mironov.tinkofftesttask.data.remote.api.FilmPageSource
import com.mironov.tinkofftesttask.data.remote.dto.FilmDetailInfoDto
import com.mironov.tinkofftesttask.data.remote.dto.FilmInfoDto
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FilmRemoteDataSource {

    fun getPopular(): Flow<PagingData<FilmInfoDto>>

    suspend fun getById(id: Int): FilmDetailInfoDto
}

class FilmRemoteDataSourceImpl @Inject constructor(
    private val api: FilmApi
) : FilmRemoteDataSource {

    override fun getPopular(): Flow<PagingData<FilmInfoDto>> = Pager(
        config = PagingConfig(pageSize = 20),
        pagingSourceFactory = {
            FilmPageSource(api)
        }
    ).flow

    override suspend fun getById(id: Int): FilmDetailInfoDto = api.getById(id)
}