package com.mironov.tinkofftesttask.presentation.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.domain.usecase.AddToFavouriteUseCase
import com.mironov.tinkofftesttask.domain.usecase.GetPopularFilmsUseCase
import com.mironov.tinkofftesttask.navigation.router.PopularRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val getPopularFilmsUseCase: GetPopularFilmsUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val router: PopularRouter
) : ViewModel() {

    private val _state = MutableStateFlow<PagingData<FilmInfo>>(PagingData.empty())
    val state = _state.asStateFlow()

    init {
        loadFilms()
    }

    fun loadFilms() {
        viewModelScope.launch {
            getPopularFilmsUseCase().cachedIn(viewModelScope).collect {
                _state.value = it
            }
        }
    }

    fun saveFavourite(film: FilmInfo) {
        viewModelScope.launch {
            addToFavouriteUseCase(film)
        }
    }

    fun openDetails(id: Int) {
        router.openDetails(id)
    }
}