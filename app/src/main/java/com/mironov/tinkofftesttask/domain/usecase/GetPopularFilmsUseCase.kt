package com.mironov.tinkofftesttask.domain.usecase

import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.domain.repository.FilmsRepository
import javax.inject.Inject

class GetPopularFilmsUseCase @Inject constructor(
    private val repository: FilmsRepository
) {

    suspend operator fun invoke(): List<FilmInfo> = repository.getPopular()
}