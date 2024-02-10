package com.mironov.tinkofftesttask.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "film")
data class FilmDbModel(
    @PrimaryKey
    val id: Int,
    val name: String,
    val year: Int,
    val genre: String,
    val posterUrl: String
)
