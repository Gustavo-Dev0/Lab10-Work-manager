package com.dev0.deliveryfood.core.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dev0.deliveryfood.core.presentation.utils.Destinations
import com.dev0.deliveryfood.feacture_order.presentation.cart.CartViewModel

@Composable
fun CartFloatingActionButton(
    navController: NavHostController,
    floatingButtonState: MutableState<Boolean>,
) {

    AnimatedVisibility(
        visible = floatingButtonState.value,

        ){
        FloatingActionButton(
            modifier = Modifier.padding(top = 0.dp),
            onClick = {
                navController.navigate(Destinations.Cart.route)
            },
        ) {

            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "")
        }
        if(CartViewModel.currentSizeOfOrders.value > 0) {
            var color =  MaterialTheme.colors.primary
            Text(
                modifier = Modifier
                    .drawBehind {
                        drawCircle(
                            color = color,
                            radius = 30F
                        )
                    },
                text = "" + CartViewModel.currentSizeOfOrders.value,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
    }

}