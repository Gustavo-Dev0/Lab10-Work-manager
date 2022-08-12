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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant

@OptIn(ExperimentalCoilApi::class)
@Composable
fun RestaurantCardContent(
    restaurant: Restaurant,
    onClick: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

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
                        modifier = Modifier.padding(8.dp).size(64.dp),
                        painter = rememberImagePainter(data = restaurant.image),
                        contentDescription = "",
                    )

                    Column() {
                        //Text(text = restaurant.id.toString())
                        Text(text = restaurant.district)
                        Text(
                            text = restaurant.name,
                            style = MaterialTheme.typography.h4.copy(
                                fontWeight = FontWeight.ExtraBold
                            )
                        )
                    }


                }

                if (expanded) {

                    Image(
                        modifier = Modifier
                            .background(color = Color.LightGray)
                            .fillMaxWidth()
                            .height(128.dp),
                        painter = rememberImagePainter(data = restaurant.image),
                        contentDescription = "",
                        contentScale = ContentScale.Crop
                    )


                    /* Row(
                         modifier = Modifier
                             .fillMaxWidth(),
                         horizontalArrangement = Arrangement.SpaceBetween
                     ) {*/
                    Text(
                        modifier = Modifier
                            .padding(vertical = 12.dp),
                        text = restaurant.description
                    )
                    Button(
                        modifier = Modifier.align(Alignment.End),
                        onClick = onClick
                    ) {
                        Text(text = "Ver platos")
                    }


                    /* }*/
                }
            }

        }
        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = { expanded = !expanded }) {
            Icon(
                imageVector = if (expanded) Icons.Filled.KeyboardArrowDown else Icons.Filled.ArrowDropDown,
                contentDescription = ""
            )
        }
    }

}