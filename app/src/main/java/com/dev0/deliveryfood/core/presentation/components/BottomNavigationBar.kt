package com.dev0.deliveryfood.core.presentation.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.dev0.deliveryfood.core.presentation.utils.Destinations
import currentRoute

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items: List<Destinations>,
    bottomBarState: MutableState<Boolean>
){
    val currentRoute = currentRoute(navController = navController)

    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            BottomNavigation {
                items.forEach{ screen ->
                    BottomNavigationItem(
                        icon = {
                            Icon(imageVector = screen.icon, contentDescription = screen.title)
                        },
                        label = { Text(text = screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            var routeD = screen.route
                            if(screen.route == "foods/{restaurantId}"){
                                routeD = Destinations.Foods.createRoute("0")
                            }

                            navController.navigate(routeD) {

                                popUpTo(navController.graph.findStartDestination().id){
                                    saveState = true
                                }
                                restoreState = true
                                launchSingleTop = true

                            }
                        },
                        alwaysShowLabel = false
                    )
                }
            }
        }
    )
}
