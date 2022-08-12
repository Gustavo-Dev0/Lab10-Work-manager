package com.dev0.deliveryfood.feacture_news.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class New(

    @PrimaryKey
    val id: Int?,
    val name: String,
    val description: String,
    val imageUrl: String,
)
