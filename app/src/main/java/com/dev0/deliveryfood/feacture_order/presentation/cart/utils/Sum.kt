package com.dev0.deliveryfood.feacture_order.presentation.cart.utils

import androidx.compose.runtime.snapshots.SnapshotStateList
import com.dev0.deliveryfood.feacture_order.domain.model.OrderDetail

fun reSum(orderDetailList: SnapshotStateList<OrderDetail>): Double {

    var r = 0.0

    for (e: OrderDetail in orderDetailList){
        r+=e.total
    }

    return r
}

fun reSum(orderDetailList: List<OrderDetail>): Double {

    var r = 0.0

    for (e: OrderDetail in orderDetailList){
        r+=e.total
    }

    return r
}