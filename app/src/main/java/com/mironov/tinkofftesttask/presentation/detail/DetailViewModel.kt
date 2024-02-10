package com.mironov.tinkofftesttask.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mironov.tinkofftesttask.domain.usecase.GetFilmInfoByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val getFilmInfoByIdUseCase: GetFilmInfoByIdUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<DetailState>(DetailState.Initial)
    val state = _state.asStateFlow()

    fun loadFilm(id: Int) {

        _state.value = DetailState.Loading

        viewModelScope.launch {
            val film = getFilmInfoByIdUseCase(id)
            _state.value = DetailState.Content(film)
        }
    }
}