package com.mironov.tinkofftesttask.domain.usecase

import com.mironov.tinkofftesttask.domain.entity.FilmDetailInfo
import com.mironov.tinkofftesttask.domain.repository.FilmsRepository
import javax.inject.Inject

class GetFilmInfoById @Inject constructor(
    private val repository: FilmsRepository
) {
    suspend operator fun invoke(id: Int): FilmDetailInfo = repository.getById(id)
}