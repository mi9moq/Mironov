package com.mironov.tinkofftesttask.data.datasource

import com.mironov.tinkofftesttask.data.remote.api.FilmApi
import com.mironov.tinkofftesttask.data.remote.dto.FilmInfoDto
import javax.inject.Inject

interface FilmRemoteDataSource {

    suspend fun getPopular(): List<FilmInfoDto>
}

class FilmRemoteDataSourceImpl @Inject constructor(
    private val api: FilmApi
) : FilmRemoteDataSource {
    override suspend fun getPopular(): List<FilmInfoDto> = api.getPopular().films
}