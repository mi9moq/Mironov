package com.mironov.tinkofftesttask.domain.entity

data class FilmDetailInfo(
    val id: Int,
    val name: String,
    val posterUrl: String,
    val year: Int,
    val countries: List<String>,
    val genres: List<String>
)
