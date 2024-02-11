package com.mironov.tinkofftesttask.data.remote.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mironov.tinkofftesttask.data.remote.dto.FilmInfoDto
import javax.inject.Inject

class FilmPageSource @Inject constructor(
    private val api: FilmApi
) : PagingSource<Int, FilmInfoDto>() {

    companion object {

        private const val MAX_PAGE = 7

        private const val START_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, FilmInfoDto>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmInfoDto> =
        try {
            val page = params.key ?: START_PAGE
            val response = api.getPopular(page = page)
            val films = response.films
            val nextKey = if (page >= MAX_PAGE) null else page + 1
            val prevKey = if (page == START_PAGE) null else page - 1
            LoadResult.Page(films, prevKey, nextKey)
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
}