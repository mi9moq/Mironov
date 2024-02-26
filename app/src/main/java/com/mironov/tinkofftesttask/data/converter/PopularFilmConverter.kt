package com.mironov.tinkofftesttask.data.converter

import com.mironov.tinkofftesttask.data.local.model.FilmDbModel
import com.mironov.tinkofftesttask.data.remote.dto.FilmInfoDto
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import javax.inject.Inject

class PopularFilmConverter @Inject constructor() {

    fun dtoToEntity(from: FilmInfoDto): FilmInfo = FilmInfo(
        id = from.id,
        name = from.name ?: from.nameOriginal ?: "",
        posterUrl = from.posterUrl,
        year = from.year,
        genres = from.genres.map { it.genre }
    )

    fun entityToDbModel(from: FilmInfo): FilmDbModel = FilmDbModel(
        id = from.id,
        name = from.name,
        genre = from.genres.first(),
        posterUrl = from.posterUrl,
        year = from.year
    )

    fun dbModelToEntity(from: FilmDbModel): FilmInfo = FilmInfo(
        id = from.id,
        name = from.name,
        posterUrl = from.posterUrl,
        year = from.year,
        genres = listOf(from.genre)
    )
}