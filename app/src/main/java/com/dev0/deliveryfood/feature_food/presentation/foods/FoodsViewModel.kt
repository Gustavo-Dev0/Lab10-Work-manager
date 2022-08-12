package com.dev0.deliveryfood.feature_food.presentation.foods

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dev0.deliveryfood.feature_food.domain.repository.FoodRepository
import com.dev0.deliveryfood.feature_food.domain.model.Food
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class FoodsViewModel @Inject constructor(
    private val repository: FoodRepository
): ViewModel(){

    private var getFoodsJob: Job? = null

    private val _state = mutableStateOf(FoodsState())
    val state: State<FoodsState> = _state

    companion object {
        var idFilterRestaurant: String = ""
    }


    val items: Flow<PagingData<Food>> = Pager(
            PagingConfig(pageSize = 4, initialLoadSize = 4)
        ){
            repository.getFoodsPagingSource()
        }.flow.cachedIn(viewModelScope)

    private fun getFoods() {
        getFoodsJob?.cancel()
        getFoodsJob = repository.getFoods()
            .onEach { foods ->
                _state.value = state.value.copy(
                    foods = foods,
                )
            }.launchIn(viewModelScope)
    }

    fun getFoodsByRestaurantId(idR: String) {

        getFoodsJob?.cancel()
        getFoodsJob = repository.getFoodsByRestaurantId(idR)
            .onEach { foods ->
                _state.value = state.value.copy(
                    foods = foods,
                )
            }.launchIn(viewModelScope)
    }
    val itemsByRestaurantId: Flow<PagingData<Food>> = Pager(
    PagingConfig(pageSize = 3, initialLoadSize = 3)
    ){
        repository.getFoodsByRestaurantIdPagingSource(idFilterRestaurant)
    }.flow.cachedIn(viewModelScope)

    fun itemsByRestaurantId(idR: String): Flow<PagingData<Food>> = Pager(
        PagingConfig(pageSize = 3, initialLoadSize = 3)
    ){
        repository.getFoodsByRestaurantIdPagingSource(idR)
    }.flow.cachedIn(viewModelScope)
}