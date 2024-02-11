package com.mironov.tinkofftesttask.data.converter

import com.mironov.tinkofftesttask.utils.FilmData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class PopularFilmConverterTest {

    private val converter = PopularFilmConverter()

    private val film = FilmData.filmInfo
    private val filmFromDb = film.copy(genres = listOf("1"))
    private val filmDto = FilmData.filmInfoDto
    private val filmDbModel = FilmData.filmInfoDbModel

    @Test
    fun `convert dto EXPECT entity`() {

        val expect = film
        val actual = converter.dtoToEntity(filmDto)

        assertEquals(expect, actual)
    }

    @Test
    fun `convert entity EXPECT DbModel`() {

        val expect = filmDbModel
        val actual = converter.entityToDbModel(film)

        assertEquals(expect, actual)
    }

    @Test
    fun `convert Db Model EXPECT entity`() {

        val expect = filmFromDb
        val actual = converter.dbModelToEntity(filmDbModel)

        assertEquals(expect, actual)
    }
}