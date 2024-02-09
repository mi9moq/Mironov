package com.mironov.tinkofftesttask.data.remote.api

import com.mironov.tinkofftesttask.data.remote.dto.PopularResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmApi {

    @GET("api/v2.2/films/collections")
    suspend fun getPopular(
        @Query("type") type: String = "TOP_POPULAR_MOVIES"
    ): PopularResponse
}