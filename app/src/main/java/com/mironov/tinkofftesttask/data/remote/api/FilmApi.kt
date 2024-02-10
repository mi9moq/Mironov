package com.mironov.tinkofftesttask.data.remote.api

import com.mironov.tinkofftesttask.data.remote.dto.FilmDetailInfoDto
import com.mironov.tinkofftesttask.data.remote.dto.PopularResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FilmApi {

    @GET("api/v2.2/films/collections")
    suspend fun getPopular(
        @Query("type") type: String = "TOP_POPULAR_MOVIES",
        @Query("page") page: Int = 1,
    ): PopularResponse

    @GET("api/v2.2/films/{id}")
    suspend fun getById(
        @Path("id") id: Int
    ): FilmDetailInfoDto
}