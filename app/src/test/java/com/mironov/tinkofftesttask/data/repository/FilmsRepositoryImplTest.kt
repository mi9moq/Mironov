package com.mironov.tinkofftesttask.data.repository

import com.mironov.tinkofftesttask.data.converter.DetailInfoConverter
import com.mironov.tinkofftesttask.data.converter.PopularFilmConverter
import com.mironov.tinkofftesttask.data.datasource.FilmLocalDataSource
import com.mironov.tinkofftesttask.data.datasource.FilmRemoteDataSource
import com.mironov.tinkofftesttask.utils.FilmData
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock
import org.junit.jupiter.api.Assertions.assertEquals
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class FilmsRepositoryImplTest {

    private val localDataSource: FilmLocalDataSource = mock()
    private val remoteDataSource: FilmRemoteDataSource = mock()
    private val popularConverter: PopularFilmConverter = mock()
    private val detailConverter: DetailInfoConverter = mock()
    private val dispatcher = StandardTestDispatcher()
    private val repository = FilmsRepositoryImpl(
        remoteDataSource = remoteDataSource,
        localDataSource = localDataSource,
        popularFilmConverter = popularConverter,
        detailInfoConverter = detailConverter,
        dispatcher = dispatcher
    )

    private val filmDetailInfo = FilmData.filmDetailInfo
    private val filmDetailInfoDto = FilmData.filmDetailInfoDto
    private val film = FilmData.filmInfo
    private val filmDbModel = FilmData.filmInfoDbModel


    @Test
    fun `get by id EXPECT film detail info`() = runTest(dispatcher) {
        whenever(remoteDataSource.getById(1)) doReturn filmDetailInfoDto
        whenever(detailConverter.convert(filmDetailInfoDto)) doReturn filmDetailInfo

        val expect = filmDetailInfo
        val actual = repository.getById(1)

        assertEquals(expect, actual)
    }

    @Test
    fun `add to favourite EXPECT save film`() = runTest(dispatcher) {
        whenever(popularConverter.entityToDbModel(film)) doReturn filmDbModel

        repository.addToFavourite(film)

        verify(localDataSource).addToFavourite(filmDbModel)
    }
}