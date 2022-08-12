package com.dev0.deliveryfood.feature_restaurant.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurants")
data class Restaurant (
    val name: String,
    val description: String,
    val district : String,
    val image : String,
    val qualification : String,

    @PrimaryKey
    val id: Int
)