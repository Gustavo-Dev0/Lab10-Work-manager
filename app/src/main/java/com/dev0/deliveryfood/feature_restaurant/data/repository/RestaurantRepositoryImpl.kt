package com.dev0.deliveryfood.feature_restaurant.data.repository

import androidx.paging.PagingSource
import com.dev0.deliveryfood.feature_restaurant.data.data_source.RestaurantDao
import com.dev0.deliveryfood.feature_restaurant.data.data_source.RestaurantPagingSource
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant
import com.dev0.deliveryfood.feature_restaurant.domain.repository.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantRepositoryImpl (
    private val dao: RestaurantDao
): RestaurantRepository {
    /*override suspend fun getById(id: Int): Restaurant {
        return dao.getRestaurantById(id)
    }*/

    override fun getRestaurants(): Flow<List<Restaurant>> {
        return dao.getAll()
    }

    override fun getRestaurantsPagingSource(): PagingSource<Int, Restaurant> {
        return RestaurantPagingSource(dao)
    }

}