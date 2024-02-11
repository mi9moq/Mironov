package com.mironov.tinkofftesttask.domain.usecase

import com.mironov.tinkofftesttask.domain.repository.FilmsRepository
import com.mironov.tinkofftesttask.utils.FilmData
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class AddToFavouriteUseCaseTest {

    private val repository: FilmsRepository = mock()
    private val useCase = AddToFavouriteUseCase(repository)

    private val film = FilmData.filmInfo

    @Test
    fun `add EXPECT save film`() = runTest {

        useCase(film)

        verify(repository).addToFavourite(film)
    }
}