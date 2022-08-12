package com.dev0.deliveryfood.feature_restaurant.presentation.restaurants

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import androidx.paging.map
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant
import com.dev0.deliveryfood.feature_restaurant.presentation.restaurants.components.ItemRestaurant
import com.dev0.deliveryfood.feature_restaurant.presentation.restaurants.components.ItemRestaurantPlaceHolder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RestaurantScreen(
    navController: NavHostController,
    navToFoods: (String) -> Unit,
    viewModel: RestaurantsViewModel = hiltViewModel()
) {
    val restaurantItems = viewModel.items.collectAsLazyPagingItems()
    val state = rememberSwipeRefreshState(
        isRefreshing = restaurantItems.loadState.refresh is LoadState.Loading,
    )

    //Log.e("RESTAURANT SCREEN", "RELOAD")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
    ) {
        /*TextField(
            value = "Buscar restaurantes",
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
            //colors = TextFieldDefaults.textFieldColors()
        )*/

        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(20.dp))

        SwipeRefresh(
            //modifier = Modifier.fillMaxSize(),
            state = state,
            onRefresh = { restaurantItems.refresh() }
        ) {
            LazyColumn(
                contentPadding = PaddingValues(
                    top = 12.dp,
                    bottom = 100.dp
                ),

            ) {

                if (restaurantItems.loadState.refresh is LoadState.NotLoading) {
                    items(
                        items = restaurantItems,
                        key = { rest ->
                            rest.id
                        }
                    ) { restaurant ->
                        if (restaurant != null) {
                            ItemRestaurant(restaurant) {
                                navToFoods(restaurant.id.toString())
                            }
                        } else {
                            ItemRestaurantPlaceHolder()
                        }

                    }

                    if (restaurantItems.loadState.append is LoadState.Loading) {
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
                }

                else if (restaurantItems.loadState.refresh is LoadState.Loading) {
                    item {
                        Box(modifier = Modifier.fillParentMaxSize()) {
                            Text(
                                text = "Cargando...",
                                modifier = Modifier.align(Alignment.Center))
                        }
                    }
                    item {
                        Box(modifier = Modifier.fillParentMaxSize()) {
                            Text(
                                text = "Cargando...",
                                modifier = Modifier.align(Alignment.Center))
                        }
                    }
                }

            }
        }
    }
}
