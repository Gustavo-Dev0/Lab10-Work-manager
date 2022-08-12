package com.dev0.deliveryfood.feature_restaurant.presentation.restaurants

import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant

data class RestaurantsState(
    val restaurants: List<Restaurant> = emptyList(),
)
