package com.mironov.tinkofftesttask.domain.entity

data class FilmInfo (
    val id: Int,
    val name: String,
    val posterUrl: String,
    val year: Int,
    val genres: List<String>
)