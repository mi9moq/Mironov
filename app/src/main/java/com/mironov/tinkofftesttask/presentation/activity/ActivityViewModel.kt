package com.mironov.tinkofftesttask.presentation.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mironov.tinkofftesttask.navigation.router.ActivityRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ActivityViewModel @Inject constructor(
    private val router: ActivityRouter
) : ViewModel() {

    private val _state = MutableStateFlow(ActivityState())
    val state = _state.asStateFlow()

    fun openPopular() {
        router.openPopular()
    }

    fun openFavourite() {
        viewModelScope.launch {
            _state.value = state.value.copy(favouriteEnable = false, popularEnable = true)
            router.openFavourite()
        }
    }

    fun back() {
        viewModelScope.launch {
            _state.value = state.value.copy(favouriteEnable = true, popularEnable = false)
            router.popBackStack()
        }
    }

    fun hideButtons() {
        viewModelScope.launch {
            _state.value = state.value.copy(popularVisibility = false, favouriteVisibility = false)
        }
    }

    fun displayButtons() {
        viewModelScope.launch {
            _state.value = state.value.copy(popularVisibility = true, favouriteVisibility = true)
        }
    }
}