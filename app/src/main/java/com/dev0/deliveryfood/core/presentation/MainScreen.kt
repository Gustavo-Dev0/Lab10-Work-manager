package com.dev0.deliveryfood.core.presentation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation.compose.rememberNavController
import com.dev0.deliveryfood.core.presentation.components.BottomNavigationBar
import com.dev0.deliveryfood.core.presentation.components.CartFloatingActionButton
import com.dev0.deliveryfood.core.presentation.components.NavigationHost
import com.dev0.deliveryfood.core.presentation.components.TopConfigBar
import com.dev0.deliveryfood.core.presentation.utils.Destinations.*
import currentRoute

@Composable
fun MainScreen(
    appThemeState: MutableState<String>,
    updateThemeInDataStore: () -> Unit
){
    val navController = rememberNavController()
    val scaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    )

    val navigateItems = listOf(
        Restaurants,
        Foods,
        History,
        News
    )

    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val topBarState = rememberSaveable { (mutableStateOf(true)) }
    val floatingButtonState = rememberSaveable { (mutableStateOf(true)) }

    when (currentRoute(navController = navController)) {
        Restaurants.route -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        Foods.route -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        History.route -> {
            bottomBarState.value = true
            topBarState.value = true
        }
        Cart.route -> {
            bottomBarState.value = false
            topBarState.value = false
        }

        News.route -> {
            bottomBarState.value = true
            topBarState.value = true
        }

        else -> {
            bottomBarState.value = false
            topBarState.value = false
        }

    }


    when (currentRoute(navController = navController)) {
        Cart.route -> {
            floatingButtonState.value = false
        }
        else -> {
            floatingButtonState.value = true
        }

    }


    Scaffold(
        topBar = {
            TopConfigBar(topBarState = topBarState, appThemeState = appThemeState, updateDataStore = updateThemeInDataStore)
        },
        scaffoldState = scaffoldState,
        bottomBar = { BottomNavigationBar(navController = navController, items = navigateItems, bottomBarState = bottomBarState) },
        floatingActionButton = {
            if (currentRoute(navController = navController) != Cart.route) {
                CartFloatingActionButton(navController = navController, floatingButtonState = floatingButtonState)
            }
        }
    ) {
        NavigationHost(navController)
    }

}
