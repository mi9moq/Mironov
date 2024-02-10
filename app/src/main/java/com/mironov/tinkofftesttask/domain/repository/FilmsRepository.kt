package com.mironov.tinkofftesttask.domain.repository

import com.mironov.tinkofftesttask.domain.entity.FilmDetailInfo
import com.mironov.tinkofftesttask.domain.entity.FilmInfo

interface FilmsRepository {

    suspend fun getPopular(): List<FilmInfo>

    suspend fun getById(id: Int): FilmDetailInfo
}