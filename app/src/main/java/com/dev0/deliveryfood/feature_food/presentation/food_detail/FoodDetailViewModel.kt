package com.dev0.deliveryfood.feature_food.presentation.food_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev0.deliveryfood.feacture_order.domain.model.OrderDetail
import com.dev0.deliveryfood.feacture_order.presentation.cart.CartViewModel
import com.dev0.deliveryfood.feature_food.domain.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FoodDetailViewModel @Inject constructor(
    private val repository: FoodRepository,
): ViewModel(){

    private var getFoodJob: Job? = null

    private val _state = mutableStateOf(FoodDetailState(null, null))
    val state: State<FoodDetailState> = _state


    fun getFoodById(id: Int) {

        getFoodJob?.cancel()
        getFoodJob = repository.getFoodById(id)
            .onEach { food ->
                _state.value = state.value.copy(
                    food = food
                )
            }.launchIn(viewModelScope)
    }

    fun addToOrder(cant: Int) {

        val newOrderDetail = OrderDetail(
            quantity = cant,
            total = cant * state.value.food!!.price,
            foodId = state.value.food!!.id,
            //restaurantName = state.value.food!!.restaurantName,
            restaurant = state.value.food!!.restaurant,
        )
        newOrderDetail.food = state.value.food!!.copy()

        CartViewModel.currentOrderHeader.total += newOrderDetail.total
        CartViewModel.currentOrderDetails.add(newOrderDetail)
        CartViewModel.currentSizeOfOrders.value = CartViewModel.currentOrderDetails.size
    }
}