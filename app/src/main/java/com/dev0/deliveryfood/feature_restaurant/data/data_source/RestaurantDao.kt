package com.dev0.deliveryfood.feature_restaurant.data.data_source

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface RestaurantDao {

    @Query("SELECT * FROM restaurants")
    fun getAll(): Flow<List<Restaurant>>

    @Query("SELECT * FROM restaurants ORDER BY restaurants.id LIMIT :loadSize OFFSET :startPosition")
    suspend fun getAll(startPosition: Int, loadSize: Int): List<Restaurant>

    @Query("SELECT COUNT(*) FROM restaurants")
    suspend fun getCount(): Int

    @Query("SELECT * from restaurants")
    fun getRestaurants(): LiveData<List<Restaurant>>

    @Query("SELECT * from restaurants WHERE id = :id")
    suspend fun getRestaurantById(id: Int): Restaurant

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurant(restaurant: Restaurant)

    @Delete
    suspend fun deleteRestaurant(restaurant: Restaurant)

}