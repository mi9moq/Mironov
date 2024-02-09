package com.mironov.tinkofftesttask.data.converter

import com.mironov.tinkofftesttask.data.remote.dto.FilmInfoDto
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import javax.inject.Inject

class PopularFilmConverter @Inject constructor() {

    fun convert(from: FilmInfoDto): FilmInfo = FilmInfo(
        id = from.id,
        name = from.name,
        posterUrl = from.posterUrl,
        year = from.year,
        genres = from.genres.map { it.genre }
    )
}