package com.mironov.tinkofftesttask.presentation.popular

import androidx.paging.PagingData
import com.mironov.tinkofftesttask.domain.entity.FilmInfo

sealed interface PopularState {

    data object Initial: PopularState

    data object Loading: PopularState

    data class Content(val content: PagingData<FilmInfo>): PopularState

    data object Error: PopularState
}