package com.mironov.tinkofftesttask.utils

import com.mironov.tinkofftesttask.data.local.model.FilmDbModel
import com.mironov.tinkofftesttask.data.remote.dto.CountryDto
import com.mironov.tinkofftesttask.data.remote.dto.FilmDetailInfoDto
import com.mironov.tinkofftesttask.data.remote.dto.FilmInfoDto
import com.mironov.tinkofftesttask.data.remote.dto.Genre
import com.mironov.tinkofftesttask.domain.entity.FilmDetailInfo
import com.mironov.tinkofftesttask.domain.entity.FilmInfo

object FilmData {

    val filmInfo = FilmInfo(
        id = 1,
        name = "name",
        posterUrl = "poster",
        year = 2019,
        genres = listOf("1","2","3")
    )

    val filmInfoDbModel = FilmDbModel(
        id = 1,
        name = "name",
        posterUrl = "poster",
        year = 2019,
        genre = "1"
    )

    val filmInfoDto = FilmInfoDto(
        id = 1,
        name = "name",
        posterUrl = "poster",
        year = 2019,
        genres = listOf(Genre("1"), Genre("2"), Genre("3")),
        nameOriginal = ""
    )

    val filmDetailInfo = FilmDetailInfo(
        id = 2,
        name = "eman",
        posterUrl = "poster",
        year = 2000,
        description = "description",
        countries = listOf("C1", "C2"),
        genres = listOf("1","2","3")
    )

    val filmDetailInfoDto = FilmDetailInfoDto(
        id = 2,
        name = "eman",
        posterUrl = "poster",
        year = 2000,
        description = "description",
        countries = listOf(CountryDto("C1"), CountryDto("C2")),
        genres = listOf(Genre("1"), Genre("2"), Genre("3"))
    )
}