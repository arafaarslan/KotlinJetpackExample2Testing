package com.example.artbook.repo

import androidx.lifecycle.LiveData
import com.example.artbook.Util.Resource
import com.example.artbook.model.Art
import com.example.artbook.model.ImageResponse

/**
 * Created by aarslan on 21/02/2022.
 */
interface ArtRepositoryInterface {

    suspend fun insertArt(art : Art)

    suspend fun deleteArt(art : Art)

    fun getArt() : LiveData<List<Art>>

    suspend fun searchImage(imageString : String) : Resource<ImageResponse>
}