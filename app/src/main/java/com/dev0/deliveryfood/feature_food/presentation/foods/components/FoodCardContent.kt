package com.dev0.deliveryfood.feature_food.presentation.foods.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dev0.deliveryfood.feature_food.domain.model.Food

@OptIn(ExperimentalCoilApi::class)
@Composable
fun FoodCardContent(food: Food) {

    Box(){
        Row(
            modifier = Modifier
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {


                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .padding(bottom = 8.dp),
                    painter = rememberImagePainter(data = food.image),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Text(
                    text = food.name,
                    style = MaterialTheme.typography.h6,
                    maxLines = 1
                )

                Text(text = food.restaurantName, style = MaterialTheme.typography.body1)

                Text(
                    text = food.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.body2,
                )


                Row() {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Favorite, contentDescription = null)
                    }

                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Default.Share, contentDescription = null)
                    }
                }
            }

        }
    }

}