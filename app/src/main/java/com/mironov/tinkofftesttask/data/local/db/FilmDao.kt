package com.mironov.tinkofftesttask.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mironov.tinkofftesttask.data.local.model.FilmDbModel
import kotlinx.coroutines.flow.Flow

@Dao
interface FilmDao {

    @Query("SELECT * FROM film")
    fun getAll(): Flow<List<FilmDbModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(film: FilmDbModel)
}