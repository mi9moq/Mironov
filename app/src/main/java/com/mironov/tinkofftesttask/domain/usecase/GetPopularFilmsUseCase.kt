package com.mironov.tinkofftesttask.domain.usecase

import androidx.paging.PagingData
import com.mironov.tinkofftesttask.domain.entity.FilmInfo
import com.mironov.tinkofftesttask.domain.repository.FilmsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPopularFilmsUseCase @Inject constructor(
    private val repository: FilmsRepository
) {

    suspend operator fun invoke(): Flow<PagingData<FilmInfo>> = repository.getPopular()
}