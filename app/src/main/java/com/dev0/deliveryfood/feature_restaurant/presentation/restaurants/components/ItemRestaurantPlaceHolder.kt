package com.dev0.deliveryfood.feature_restaurant.presentation.restaurants.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.dev0.deliveryfood.R
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant

@Composable
fun ItemRestaurantPlaceHolder(){
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


                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Image(
                            modifier = Modifier
                                .padding(8.dp)
                                .size(64.dp),
                            painter = painterResource(id = R.drawable.ic_test_background),
                            contentDescription = "",
                        )

                        Column() {
                            Text(text = "Loading")
                            Text(text = "Loading")
                            Text(
                                text = "Loading",
                                style = MaterialTheme.typography.h4.copy(
                                    fontWeight = FontWeight.ExtraBold
                                )
                            )
                        }


                    }
                }

            }
        }
    }
}