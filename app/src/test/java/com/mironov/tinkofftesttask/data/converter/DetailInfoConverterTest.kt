package com.mironov.tinkofftesttask.data.converter

import com.mironov.tinkofftesttask.utils.FilmData
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DetailInfoConverterTest {

    private val converter = DetailInfoConverter()

    private val film = FilmData.filmDetailInfo
    private val filmDto = FilmData.filmDetailInfoDto

    @Test
    fun `convert Dto EXPECT entity`(){

        val expected = film
        val actual = converter.convert(filmDto)

        assertEquals(expected, actual)
    }
}