package com.dev0.deliveryfood.feacture_order.data.data_source

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev0.deliveryfood.feacture_order.domain.model.OrderDetail
import com.dev0.deliveryfood.feacture_order.domain.model.OrderHeader
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(orderDetail: OrderDetail)

    @Query("SELECT * FROM orders")
    fun getAll(): Flow<List<OrderHeader>>

}