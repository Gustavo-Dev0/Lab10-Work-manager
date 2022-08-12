package com.dev0.deliveryfood.feature_restaurant.domain.repository

import android.database.sqlite.SQLiteDatabase
import androidx.compose.ui.platform.LocalContext
import androidx.paging.PagingSource
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {

    fun getRestaurants(): Flow<List<Restaurant>>
    fun getRestaurantsPagingSource(): PagingSource<Int, Restaurant>
}