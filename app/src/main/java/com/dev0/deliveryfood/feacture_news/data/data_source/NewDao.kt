package com.dev0.deliveryfood.feacture_news.data.data_source

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.dev0.deliveryfood.feacture_news.domain.model.New
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NewDao {

    @Query("SELECT * FROM news")
    fun getAll(): Flow<List<New>>

    @Query("DELETE FROM news")
    fun deleteAll()

    @Insert
    fun insert(n: New)

}