package com.dev0.deliveryfood.feacture_order.presentation.cart

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.dev0.deliveryfood.feacture_order.domain.model.OrderHeader
import com.dev0.deliveryfood.feacture_order.domain.model.OrderDetail
import com.dev0.deliveryfood.feacture_order.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: OrderRepository
): ViewModel() {

    private val _state = mutableStateOf(CartState())
    val state: State<CartState> = _state


    init {
        getOrder()
    }

    companion object {
        var currentOrderHeader: OrderHeader = OrderHeader()
        var currentOrderDetails: MutableList<OrderDetail> = mutableListOf()
        var currentSizeOfOrders: MutableState<Int> = mutableStateOf(0)
    }


    private fun getOrder() {

        _state.value.orderDetails.clear()
        _state.value.orderDetails.addAll(ArrayList<OrderDetail>(currentOrderDetails))

        _state.value.orderHeader.total = currentOrderHeader.total

        currentSizeOfOrders.value = _state.value.orderDetails.size
        Log.e("  ", "Hace el get con "+_state.value.orderDetails.size)
    }

    suspend fun makeOrder() {

        repository.saveOrderWithDetails(_state.value.orderHeader, _state.value.orderDetails)
        currentOrderDetails.clear()
        currentOrderHeader.total = 0.0
        currentSizeOfOrders.value = 0

       _state.value.orderDetails.clear()
    }

}