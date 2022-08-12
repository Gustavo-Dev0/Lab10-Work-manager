package com.dev0.deliveryfood.core.presentation.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Destinations(
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    object Restaurants: Destinations("restaurants", "Restaurantes", Icons.Filled.Restaurant)
    object Foods: Destinations("foods/{restaurantId}", "Comidas", Icons.Filled.FoodBank){
        fun createRoute(restaurantId: String) = "foods/$restaurantId"
    }
    object FoodDetail: Destinations("foodDetail/{foodId}", "Detalles de comida", Icons.Filled.Settings){
        fun createRoute(foodId: Int) = "foodDetail/$foodId"
    }
    object Cart: Destinations("cart", "Carro de compras", Icons.Filled.Favorite)
    object History: Destinations("history", "Historial", Icons.Filled.History)
    object News: Destinations("news", "Noticias", Icons.Filled.Notifications)
}