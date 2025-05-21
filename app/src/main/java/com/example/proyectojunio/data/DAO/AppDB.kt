package com.example.proyectojunio.data.DAO

import android.content.Context
import androidx.room.Database
import androidx.room.Room

import androidx.room.RoomDatabase
import com.example.proyectojunio.data.model.SerieDb

@Database(entities = [SerieDb::class], version = 1)
abstract class AppDB : RoomDatabase(){
    abstract fun serieDao() : SerieDao

    companion object{
        @Volatile
        private var INSTANCE: AppDB? = null

        fun getDatabase(context: Context): AppDB {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDB::class.java,
                    "serie_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}