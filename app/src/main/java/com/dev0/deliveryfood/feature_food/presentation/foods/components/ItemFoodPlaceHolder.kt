package com.dev0.deliveryfood.feature_food.presentation.foods.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.dev0.deliveryfood.R
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant

@Composable
fun ItemFoodPlaceHolder(){
    Card(
        backgroundColor = MaterialTheme.colors.onPrimary,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        elevation = 8.dp,
    ) {

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
                        painter = painterResource(id = R.drawable.ic_test_background),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        text = "Nombre de comida",
                        style = MaterialTheme.typography.h6,
                        maxLines = 1
                    )

                    Text(text = "Nombre de restaurante", style = MaterialTheme.typography.body1)

                    Text(
                        text = "Descripcion de comida",
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
}