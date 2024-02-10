package com.mironov.tinkofftesttask.domain.usecase

import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.domain.repository.FilmsRepository
import javax.inject.Inject

class AddToFavouriteUseCase @Inject constructor(
    private val repository: FilmsRepository
) {

    suspend operator fun invoke(film: FilmInfo) = repository.addToFavourite(film)
}