package com.dev0.deliveryfood.feature_food.data.repository

import androidx.paging.PagingSource
import com.dev0.deliveryfood.feature_food.data.data_source.FoodDao
import com.dev0.deliveryfood.feature_food.data.data_source.FoodPagingSource
import com.dev0.deliveryfood.feature_food.domain.model.Food
import com.dev0.deliveryfood.feature_food.domain.repository.FoodRepository
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

class FoodRepositoryImpl(
    val dao: FoodDao
): FoodRepository {

    override fun getFoodById(id: Int): Flow<Food> {
        return dao.getById(id)
    }

    //override fun getRestaurantById(id: Int): Restaurant {}

    override fun getFoodsByRestaurantIdPagingSource(idR: String): PagingSource<Int, Food> {
        return FoodPagingSource(dao, idR)
    }

    override fun getFoodsByRestaurantId(idR: String): Flow<List<Food>> {
        return dao.getAllByRestaurantId(idR)
    }

    override fun getFoods(): Flow<List<Food>> {
        return dao.getAll()
    }

    override fun getFoodsPagingSource(): PagingSource<Int, Food> {
        return FoodPagingSource(dao, null)
    }

}