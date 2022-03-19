package com.example.artbook.model

/**
 * Created by aarslan on 20/02/2022.
 */
data class ImageResponse(
    val hits : List<ImageResult>,
    val total : Int,
    val totalHits : Int
)
