package com.dev0.deliveryfood.feature_food.presentation.foods

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.dev0.deliveryfood.core.presentation.utils.Destinations
import com.dev0.deliveryfood.feature_food.domain.model.Food
import com.dev0.deliveryfood.feature_food.presentation.foods.components.ItemFood
import com.dev0.deliveryfood.feature_food.presentation.foods.components.ItemFoodPlaceHolder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun FoodScreen(
    navController: NavHostController,
    restaurantId: String,
    viewModel: FoodsViewModel = hiltViewModel()
) {

    if (restaurantId == "0") {
        val foodItems = viewModel.items.collectAsLazyPagingItems()
        val state = rememberSwipeRefreshState(
            isRefreshing = foodItems.loadState.refresh is LoadState.Loading
        )

       // Log.e("FOOD SCREEN", "RELOAD" + (foodItems?.loadState?.refresh is LoadState.Loading))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            //TEst
            //Text(text = restaurantId)
            //
            /*TextField(
                value = "Buscar comidas",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Filled.Search,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(12.dp)
                            .size(24.dp)
                    )
                },
                trailingIcon = {

                },
                singleLine = true,
                shape = RectangleShape,
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )*/

            SwipeRefresh(
                state = state,
                onRefresh = { foodItems.refresh() }
            ) {
                LazyVerticalGrid(
                    cells = GridCells.Adaptive(128.dp),
                    contentPadding = PaddingValues(
                        top = 12.dp,
                        bottom = 100.dp
                    )
                ) {
                    if (foodItems.loadState.refresh is LoadState.NotLoading) {

                        items(foodItems.itemCount) { index ->
                            val actualFood: Food? = foodItems[index]
                            if (actualFood != null) {
                                ItemFood(foodItems[index]!!) {
                                    //Log.e("IDFOOD", ""+actualFood.id)
                                    navController.navigate(
                                        Destinations.FoodDetail.createRoute(
                                            actualFood.id
                                        )
                                    )
                                }
                            } else {
                                ItemFoodPlaceHolder()
                            }

                        }

                        if (foodItems.loadState.append is LoadState.Loading) {
                            item {
                                Box(modifier = Modifier.fillMaxWidth()) {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .align(Alignment.Center)
                                            .padding(16.dp)
                                    )
                                }
                            }
                        }
                    } else if (foodItems.loadState.refresh is LoadState.Loading) {
                        Log.e("", "ESTADO ERCARGANDOP")
                        item {
                            Box(modifier = Modifier.fillParentMaxSize()) {
                                Text(
                                    text = "Cargando...",
                                    modifier = Modifier.align(Alignment.Center)
                                )
                            }
                        }
                    }
                }

            }

        }
    } else {

        viewModel.getFoodsByRestaurantId(restaurantId)
        val state = viewModel.state

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {

            TextField(
                value = "Buscar comidas",
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth(),
                leadingIcon = {
                    Icon(
                        imageVector = Filled.Search,
                        contentDescription = "",
                        modifier = Modifier
                            .padding(12.dp)
                            .size(24.dp)
                    )
                },
                trailingIcon = {

                },
                singleLine = true,
                shape = RectangleShape,
            )

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
            )

            LazyVerticalGrid(
                cells = GridCells.Adaptive(128.dp),
                contentPadding = PaddingValues(
                    top = 12.dp,
                    bottom = 100.dp
                )
            ) {
                items(state.value.foods.size) { index ->
                    ItemFood(state.value.foods[index]) {
                        navController.navigate(
                            Destinations.FoodDetail.createRoute(
                                state.value.foods[index].id
                            )
                        )
                    }

                }
            }

        }

    }
}