package com.mironov.tinkofftesttask.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mironov.tinkofftesttask.data.local.model.FilmDbModel

@Database(entities = [FilmDbModel::class], version = 1, exportSchema = false)
abstract class FilmDataBase: RoomDatabase() {

    abstract fun filmDao(): FilmDao
}