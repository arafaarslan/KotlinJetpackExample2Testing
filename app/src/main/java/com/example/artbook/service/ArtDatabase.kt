package com.example.artbook.service

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.artbook.model.Art

/**
 * Created by aarslan on 20/02/2022.
 */
@Database(entities = [Art::class], version = 1, exportSchema = false)
abstract class ArtDatabase : RoomDatabase() {
    abstract fun artDao() : ArtDao
}