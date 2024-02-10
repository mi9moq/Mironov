package com.mironov.tinkofftesttask.domain.usecase

import com.mironov.tinkofftesttask.domain.repository.FilmsRepository
import javax.inject.Inject

class GetFavouriteUseCase @Inject constructor(
    private val repository: FilmsRepository
) {

    operator fun invoke() = repository.getFavourite()
}