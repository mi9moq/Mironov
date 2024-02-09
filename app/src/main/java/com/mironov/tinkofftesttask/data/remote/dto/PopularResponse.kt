package com.mironov.tinkofftesttask.data.remote.dto

import com.google.gson.annotations.SerializedName

data class PopularResponse (
    @SerializedName("items")
    val films: List<FilmInfoDto>,
)