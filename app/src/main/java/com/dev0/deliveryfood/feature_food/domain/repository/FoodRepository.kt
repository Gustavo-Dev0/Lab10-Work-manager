package com.dev0.deliveryfood.feature_food.domain.repository

import androidx.paging.PagingSource
import com.dev0.deliveryfood.feature_food.domain.model.Food
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface FoodRepository {
    fun getFoodsPagingSource(): PagingSource<Int, Food>
    fun getFoods(): Flow<List<Food>>
    fun getFoodById(id: Int): Flow<Food>
    fun getFoodsByRestaurantIdPagingSource(idR: String): PagingSource<Int, Food>
    fun getFoodsByRestaurantId(idR: String): Flow<List<Food>>
}