package com.example.artbook.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.artbook.model.Art

/**
 * Created by aarslan on 20/02/2022.
 */
@Dao
interface ArtDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArt(art : Art)

    @Delete
    suspend fun deleteArt(art : Art)

    @Query("SELECT * from arts")
    fun observeArts(): LiveData<List<Art>>
}