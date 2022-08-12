package com.dev0.deliveryfood.feacture_news.domain.repository

import com.dev0.deliveryfood.feacture_news.domain.model.New
import kotlinx.coroutines.flow.Flow

interface NewRepository {

    fun getNews(): Flow<List<New>>
    fun deleteNews()
    fun insert(n: New)
}