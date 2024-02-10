package com.mironov.tinkofftesttask.presentation.popular

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.domain.usecase.AddToFavouriteUseCase
import com.mironov.tinkofftesttask.domain.usecase.GetPopularFilmsUseCase
import com.mironov.tinkofftesttask.navigation.router.PopularRouter
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val getPopularFilmsUseCase: GetPopularFilmsUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val router: PopularRouter
) : ViewModel() {

    private val _state = MutableStateFlow<PopularState>(PopularState.Initial)
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, _ ->
        _state.value = PopularState.Error
    }

    init {
        loadFilms()
    }

    fun loadFilms() {
        _state.value = PopularState.Loading

        viewModelScope.launch(handler) {
            getPopularFilmsUseCase().cachedIn(viewModelScope).collect {
                _state.value = PopularState.Content(it)
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