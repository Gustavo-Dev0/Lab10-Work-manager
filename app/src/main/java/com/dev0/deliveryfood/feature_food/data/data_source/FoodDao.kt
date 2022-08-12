package com.dev0.deliveryfood.feature_food.data.data_source

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dev0.deliveryfood.feature_food.domain.model.Food
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodDao {

    @Query("SELECT * FROM foods ORDER BY foods.id LIMIT :loadSize OFFSET :startPosition")
    suspend fun getAll(startPosition: Int, loadSize: Int): List<Food>

    @Query("SELECT * FROM foods WHERE foods.restaurant=:idR ORDER BY foods.id LIMIT :loadSize OFFSET :startPosition")
    suspend fun getAllByRestaurantId(startPosition: Int, loadSize: Int, idR: String): List<Food>

    @Query("SELECT * FROM foods WHERE foods.restaurant=:idR")
    fun getAll(idR: String): Flow<List<Food>>

    @Query("SELECT * FROM foods WHERE id=:id")
    fun getById(id: Int): Flow<Food>

    @Query("SELECT COUNT(*) FROM foods")
    suspend fun getCount(): Int

    @Query("SELECT COUNT(*) FROM foods where foods.restaurant=:idR")
    suspend fun getCountByRestaurantId(idR: String): Int

    @Query("SELECT * from foods")
    fun getAll(): Flow<List<Food>>

    @Query("SELECT * from foods WHERE restaurant = :id")
    fun getAllByRestaurantId(id: String): Flow<List<Food>>

    @Query("SELECT * from foods WHERE id = :id")
    fun getFoodById(id: Int): LiveData<Food>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)


}