package com.mironov.tinkofftesttask.data.datasource

import com.mironov.tinkofftesttask.data.local.db.FilmDao
import com.mironov.tinkofftesttask.utils.FilmData
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify

class FilmLocalDataSourceImplTest {

    private val dao: FilmDao = mock()
    private val dataSource = FilmLocalDataSourceImpl(dao)

    private val film = FilmData.filmInfoDbModel

    @Test
    fun `add EXPECT save in data base`() = runTest {

        dataSource.addToFavourite(film)

        verify(dao).add(film)
    }
}