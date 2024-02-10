package com.mironov.tinkofftesttask.presentation.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mironov.tinkofftesttask.domain.usecase.GetFavouriteUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class FavouriteViewModel @Inject constructor(
    private val getFavouriteUseCase: GetFavouriteUseCase
) : ViewModel() {

    val state: StateFlow<FavouriteState> = getFavouriteUseCase().map {
        FavouriteState.Content(it)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = FavouriteState.Initial
    )
}