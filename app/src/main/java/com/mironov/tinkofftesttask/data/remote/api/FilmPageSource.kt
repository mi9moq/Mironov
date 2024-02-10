package com.mironov.tinkofftesttask.data.remote.api

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mironov.tinkofftesttask.data.remote.dto.FilmInfoDto
import kotlinx.coroutines.delay
import javax.inject.Inject

class FilmPageSource @Inject constructor(
    private val api: FilmApi
) : PagingSource<Int, FilmInfoDto>() {

    companion object {

        private const val MAX_PAGE = 7
    }

    override fun getRefreshKey(state: PagingState<Int, FilmInfoDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmInfoDto> =
        try {
            val page = params.key ?: 1
            val response = api.getPopular(page = page)
            val films = response.films
            val nextKey = if (page >= MAX_PAGE) null else page + 1
            val prevKey = if (page == 1) null else page - 1
            LoadResult.Page(films, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}