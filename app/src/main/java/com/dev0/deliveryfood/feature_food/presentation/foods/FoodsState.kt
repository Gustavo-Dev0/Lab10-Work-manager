package com.dev0.deliveryfood.feature_food.presentation.foods

import com.dev0.deliveryfood.feature_food.domain.model.Food

data class FoodsState(
    val foods: List<Food> = emptyList(),
)
