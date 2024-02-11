package com.mironov.tinkofftesttask.domain.usecase

import com.mironov.tinkofftesttask.domain.repository.FilmsRepository
import com.mironov.tinkofftesttask.utils.FilmData
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class GetFilmInfoByIdUseCaseTest {

    private val repository: FilmsRepository = mock()
    private val useCase = GetFilmInfoByIdUseCase(repository)

    private val film = FilmData.filmDetailInfo

    @Test
    fun `get by id EXPECT film`() = runTest {
        whenever(repository.getById(1)) doReturn film

        val expected = film
        val actual = useCase(1)

        assertEquals(expected,actual)
    }
}