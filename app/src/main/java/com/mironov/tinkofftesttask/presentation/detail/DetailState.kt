package com.mironov.tinkofftesttask.presentation.detail

import com.mironov.tinkofftesttask.domain.entity.FilmDetailInfo

sealed interface DetailState {

    data object Initial: DetailState

    data object Loading: DetailState

    data class Content(val content: FilmDetailInfo): DetailState

    data object Error: DetailState
}