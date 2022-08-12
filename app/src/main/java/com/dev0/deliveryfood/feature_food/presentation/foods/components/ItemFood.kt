package com.dev0.deliveryfood.feature_food.presentation.foods.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev0.deliveryfood.feature_food.domain.model.Food

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ItemFood(
    item: Food,
    onClick: () -> Unit
){
    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
        onClick = onClick
    ) {
        FoodCardContent(food = item)
    }
}