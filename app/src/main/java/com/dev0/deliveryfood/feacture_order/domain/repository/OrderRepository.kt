package com.dev0.deliveryfood.feacture_order.domain.repository

import com.dev0.deliveryfood.feacture_order.domain.model.OrderDetail
import com.dev0.deliveryfood.feacture_order.domain.model.OrderHeader
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun saveOrderWithDetails(orderHeader: OrderHeader, orderDetails: List<OrderDetail>)
    fun getOrders(): Flow<List<OrderHeader>>
}