package com.mironov.tinkofftesttask.data.datasource

import com.mironov.tinkofftesttask.data.local.db.FilmDao
import com.mironov.tinkofftesttask.data.local.model.FilmDbModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface FilmLocalDataSource {

    suspend fun addToFavourite(film: FilmDbModel)

    fun getFavourite(): Flow<List<FilmDbModel>>
}

class FilmLocalDataSourceImpl @Inject constructor(
    private val dao: FilmDao
) : FilmLocalDataSource {

    override suspend fun addToFavourite(film: FilmDbModel) = dao.add(film)

    override fun getFavourite(): Flow<List<FilmDbModel>> = dao.getAll()
}