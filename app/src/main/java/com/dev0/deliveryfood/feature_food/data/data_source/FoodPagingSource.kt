package com.dev0.deliveryfood.feature_food.data.data_source

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.dev0.deliveryfood.feature_food.domain.model.Food
import kotlinx.coroutines.delay
import kotlin.math.max

private const val STARTING_KEY = 1


class FoodPagingSource(
    private var dao: FoodDao,
    private var idR: String?
) : PagingSource<Int, Food>() {

    override suspend fun load(
        params: LoadParams<Int>,
    ): LoadResult<Int, Food> {
        delay(2000)
        val position = params.key ?: STARTING_KEY
        //if(position > 1) delay(2000)
        Log.e("PagingSource", "$position")
        val limit = if(idR == null) {
            dao.getCount()
        } else {
            dao.getCountByRestaurantId(idR!!)
        }
        val response = if(idR == null){
            dao.getAll((position-1)*params.loadSize, params.loadSize)
        } else {
            dao.getAllByRestaurantId((position-1)*params.loadSize, params.loadSize, idR!!)
        }
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

    override fun getRefreshKey(state: PagingState<Int, Food>): Int? {

        val anchorPosition = state.anchorPosition ?: return null
        val article = state.closestItemToPosition(anchorPosition) ?: return null
        return ensureValidKey(key = article.id - (state.config.pageSize / 2))
    }

    private fun ensureValidKey(key: Int?) = max(STARTING_KEY, key!!)
}