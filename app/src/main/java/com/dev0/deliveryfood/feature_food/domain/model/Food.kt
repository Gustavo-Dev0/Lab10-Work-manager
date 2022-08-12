package com.dev0.deliveryfood.feature_food.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "foods")
data class Food (
    val name: String,
    val description: String,
    val restaurant : Int,
    val restaurantName : String,
    val qualification : String,
    val image : String,
    val price : Double,
    @PrimaryKey
    val id: Int
)