package com.mironov.tinkofftesttask.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FilmDetailInfoDto(
    @SerializedName("kinopoiskId")
    val id: Int,
    @SerializedName("nameRu")
    val name: String,
    val posterUrl: String,
    val description: String?,
    val year: Int,
    val countries: List<CountryDto>,
    val genres: List<Genre>
)
