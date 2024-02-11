package com.mironov.tinkofftesttask.data.datasource

import com.mironov.tinkofftesttask.data.remote.api.FilmApi
import com.mironov.tinkofftesttask.utils.FilmData
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class FilmRemoteDataSourceImplTest {

    private val api: FilmApi = mock()
    private val dataSource = FilmRemoteDataSourceImpl(api)

    private val film = FilmData.filmDetailInfoDto

    @Test
    fun `get by id EXPECT filmDetailInfoDto`() = runTest {
        whenever(api.getById(1)) doReturn film

        val expect = film
        val actual = dataSource.getById(1)

        assertEquals(expect, actual)
    }
}