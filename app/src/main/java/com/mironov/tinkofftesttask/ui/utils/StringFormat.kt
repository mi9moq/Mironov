package com.mironov.tinkofftesttask.ui.utils

fun formatFilmDescription(genre: String, year: Int): String {
    val genreTitleCase = genre.replaceFirstChar {
        it.titlecase()
    }
    return "$genreTitleCase ($year)"
}