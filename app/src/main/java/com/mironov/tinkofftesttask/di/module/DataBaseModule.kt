package com.mironov.tinkofftesttask.di.module

import android.app.Application
import androidx.room.Room
import com.mironov.tinkofftesttask.data.local.db.FilmDao
import com.mironov.tinkofftesttask.data.local.db.FilmDataBase
import com.mironov.tinkofftesttask.di.annotation.AppScope
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    companion object{

        private const val DB_NAME = "films.db"
    }

    @AppScope
    @Provides
    fun provideFilmDatabase(application: Application): FilmDataBase = Room.databaseBuilder(
        application,
        FilmDataBase::class.java,
        DB_NAME
    ).build()

    @AppScope
    @Provides
    fun provideFilmDao(dataBase: FilmDataBase): FilmDao =
        dataBase.filmDao()
}