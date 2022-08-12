package com.dev0.deliveryfood.feacture_order.data.repository

import com.dev0.deliveryfood.feacture_order.data.data_source.OrderDetailDao
import com.dev0.deliveryfood.feacture_order.data.data_source.OrderHeaderDao
import com.dev0.deliveryfood.feacture_order.domain.model.OrderDetail
import com.dev0.deliveryfood.feacture_order.domain.model.OrderHeader
import com.dev0.deliveryfood.feacture_order.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(
    private var daoHeaderDao: OrderHeaderDao,
    private val daoDetailDao: OrderDetailDao
): OrderRepository {


    override suspend fun saveOrderWithDetails(orderHeader: OrderHeader, orderDetails: List<OrderDetail>) {
        orderHeader.user = "Phone"
        val id: Int = daoHeaderDao.insert(orderHeader).toInt()

        orderDetails.forEach{ orderDetail ->
            orderDetail.orderId = id
            daoDetailDao.insert(orderDetail)
        }
    }

    override fun getOrders(): Flow<List<OrderHeader>> {
        return daoHeaderDao.getAll()
    }
}