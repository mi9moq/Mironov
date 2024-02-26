package com.mironov.tinkofftesttask.data.remote.dto

import com.google.gson.annotations.SerializedName

data class FilmInfoDto (
    @SerializedName("kinopoiskId")
    val id: Int,
    @SerializedName("nameRu")
    val name: String?,
    val nameOriginal: String?,
    val posterUrl: String,
    val year: Int,
    val genres: List<Genre>
)