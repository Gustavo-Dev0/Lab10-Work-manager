package com.dev0.deliveryfood.feature_restaurant.data.data_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev0.deliveryfood.core.data.DeliveryDatabase
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.takeWhile
import javax.inject.Inject
import javax.inject.Named
import kotlin.math.max

private const val STARTING_KEY = 1


class RestaurantPagingSource(
    private var dao: RestaurantDao
) : PagingSource<Int, Restaurant>() {

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, Restaurant> {

        delay(2000)
        val position = params.key ?: STARTING_KEY
        Log.e("PagingSource", "$position")
        val response = dao.getAll((position-1)*params.loadSize, params.loadSize)
        val limit = dao.getCount()
        val itemsBefore = position * params.loadSize
        //val itemsAfter = limit - itemsBefore

        return LoadResult.Page(
            data = response,
            prevKey = if(position-1 > 0) position-1 else null,
            nextKey = if(position*params.loadSize >= limit) null else position+1,
            itemsBefore = if(position-1 > 0) itemsBefore else 0,
            itemsAfter = if(position*params.loadSize >= limit) 0 else 1
        )
    }

    override fun getRefreshKey(state: PagingState<Int, Restaurant>): Int? {

        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
    }

    private fun ensureValidKey(key: Int?) = max(STARTING_KEY, key!!)
}