package com.dev0.deliveryfood.feacture_news.presentation.news.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dev0.deliveryfood.feacture_news.domain.model.New
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant

@Composable
fun ItemNew(
    item: New
){
    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {
        NewCardContent(item)
    }
}