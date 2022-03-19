package com.example.artbook.api

import com.example.artbook.Util.Util
import com.example.artbook.model.ImageResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by aarslan on 20/02/2022.
 */
interface RetrofitApi {

    @GET("/api/")
    suspend fun imageSearch(
        @Query("q")searchQuery: String,
        @Query("key")apiKey: String = Util.API_KEY,
    ) : Response<ImageResponse>
}