package com.mironov.tinkofftesttask.data.converter

import com.mironov.tinkofftesttask.data.remote.dto.FilmDetailInfoDto
import com.mironov.tinkofftesttask.domain.entity.FilmDetailInfo
import javax.inject.Inject

class DetailInfoConverter @Inject constructor() {

    fun convert(from: FilmDetailInfoDto): FilmDetailInfo = FilmDetailInfo(
        id = from.id,
        name = from.name ?: from.nameOriginal ?: "",
        posterUrl = from.posterUrl,
        year = from.year,
        countries = from.countries.map { it.country },
        genres = from.genres.map { it.genre },
        description = from.description ?: EMPTY_DESCRIPTION,
    )

    companion object {

        private const val EMPTY_DESCRIPTION =""
    }
}