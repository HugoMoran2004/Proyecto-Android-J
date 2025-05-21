package com.example.proyectojunio.data.DAO

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.proyectojunio.data.model.SerieDb

@Dao
interface SerieDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSerie(serie: SerieDb)

    @Query("SELECT * FROM series WHERE id = :id")
    suspend fun getSerieById(id: Int): SerieDb?

    @Query("SELECT * FROM series")
    suspend fun getAllSeries(): List<SerieDb>

    @Delete
    suspend fun deleteSerie(serie: SerieDb)
}