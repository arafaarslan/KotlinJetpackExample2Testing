package com.example.artbook.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by aarslan on 20/02/2022.
 */
@Entity(tableName = "arts")
data class Art(
    var name : String,
    var artistName : String,
    var year : Int,
    var imageUrl : String,
    @PrimaryKey(autoGenerate = true)
    var id : Int? = null
)