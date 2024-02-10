package com.mironov.tinkofftesttask.presentation.favourite

import com.mironov.tinkofftesttask.domain.entity.FilmInfo

sealed interface FavouriteState {

    data object Initial: FavouriteState

    data class Content(val content: List<FilmInfo>): FavouriteState
}