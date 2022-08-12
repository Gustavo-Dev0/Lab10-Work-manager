package com.dev0.deliveryfood.feature_food.presentation.food_detail

import com.dev0.deliveryfood.feature_food.domain.model.Food


data class FoodDetailState(
    val food: Food?,
    val restaurant: String?
)