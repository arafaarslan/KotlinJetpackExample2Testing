package com.example.artbook.model

import com.google.gson.annotations.SerializedName

/**
 * Created by aarslan on 20/02/2022.
 */
class ImageResult(
    val id: Int,
    val pageURL: String,
    val type: String,
    val tags: String,
    val previewURL: String,
    val previewWidth: Int,
    val previewHeight: Int,
    val webformatURL: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val views: Int,
    val downloads: Int,
    val collections: Int,
    val likes: Int,
    val comments: Int,
    @SerializedName("user_id")
    val userId: Int,
    val user: String,
    val userImageURL: String,

    )