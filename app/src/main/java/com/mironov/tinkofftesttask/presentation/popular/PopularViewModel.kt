package com.mironov.tinkofftesttask.presentation.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mironov.tinkofftesttask.domain.usecase.GetPopularFilmsUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularViewModel @Inject constructor(
    private val getPopularFilmsUseCase: GetPopularFilmsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<PopularState>(PopularState.Initial)
    val state = _state.asStateFlow()

    private val handler = CoroutineExceptionHandler { _, _  ->
        _state.value = PopularState.Error
    }

    init {
        loadFilms()
    }

    fun loadFilms() {
        _state.value = PopularState.Loading

        viewModelScope.launch(handler) {
            val films = getPopularFilmsUseCase()
            _state.value = PopularState.Content(films)
        }
    }
}