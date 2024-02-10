package com.mironov.tinkofftesttask.domain.repository

import androidx.paging.PagingData
import com.mironov.tinkofftesttask.domain.entity.FilmDetailInfo
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import kotlinx.coroutines.flow.Flow

interface FilmsRepository {

    fun getPopular(): Flow<PagingData<FilmInfo>>

    suspend fun getById(id: Int): FilmDetailInfo
}