package com.dev0.deliveryfood.feacture_order.data.data_source

import androidx.room.*
import com.dev0.deliveryfood.feacture_order.domain.model.OrderHeader
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderHeaderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderHeader: OrderHeader): Long

    @Query("SELECT * FROM orders")
    fun getAll(): Flow<List<OrderHeader>>
}