package com.dev0.deliveryfood.feacture_order.presentation.cart

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.dev0.deliveryfood.feacture_order.domain.model.OrderHeader
import com.dev0.deliveryfood.feacture_order.domain.model.OrderDetail

data class CartState(
    var orderDetails: SnapshotStateList<OrderDetail> = mutableStateListOf(),
    var orderHeader: OrderHeader = OrderHeader()
)