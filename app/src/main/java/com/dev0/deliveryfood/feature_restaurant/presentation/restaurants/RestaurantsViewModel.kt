package com.dev0.deliveryfood.feature_restaurant.presentation.restaurants

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant
import com.dev0.deliveryfood.feature_restaurant.domain.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class RestaurantsViewModel @Inject constructor(
    val repository: RestaurantRepository
    ): ViewModel() {

    private var getRestaurantsJob: Job? = null

    private val _state = mutableStateOf(RestaurantsState())
    val state: State<RestaurantsState> = _state

    val items: Flow<PagingData<Restaurant>> = Pager(
        config = PagingConfig(pageSize = 3, initialLoadSize = 3)
    ) {
        repository.getRestaurantsPagingSource()
    }.flow.cachedIn(viewModelScope)


    private fun getRestaurants() {
        getRestaurantsJob?.cancel()
        getRestaurantsJob = repository.getRestaurants()
            .onEach { restaurants ->
                _state.value = state.value.copy(
                    restaurants = restaurants,
                )
        }.launchIn(viewModelScope)
    }

}