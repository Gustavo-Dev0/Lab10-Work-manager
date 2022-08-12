package com.dev0.deliveryfood.feacture_order.presentation.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.alpha
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.dev0.deliveryfood.core.presentation.utils.Destinations
import com.dev0.deliveryfood.feacture_order.presentation.cart.components.ItemOrder
import com.dev0.deliveryfood.feacture_order.presentation.cart.utils.reSum
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun CartScreen(
    navController: NavHostController,
    title:String,
    viewModel: CartViewModel = hiltViewModel()
) {

    var void by remember { mutableStateOf(true) }
    var buttonShow by remember { mutableStateOf(true)}

    val scope = rememberCoroutineScope()

    val state = viewModel.state.value

    void = state.orderDetails.size == 0

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        //verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Platos elegidos",
            style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
        )
        if(void){
            if(!buttonShow){
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally

                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            //.align(Alignment.Center)
                            .padding(16.dp)
                    )
                    Text(
                        modifier = Modifier.padding(top = 300.dp),
                        text = "Enviando pedido(s)...",
                        style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
                    )
                }
            }
            Text(
                modifier = Modifier.padding(top = 300.dp),
                text = "Esto está vacío",
                style = TextStyle(color = Color.Black, fontSize = 42.sp, fontWeight = FontWeight.Black)
            )
        }else{
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding()
                    .padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {
                Text(
                    text = "Total: "+state.orderHeader.total,
                    style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )

                Button(
                    modifier = Modifier.alpha(if(buttonShow) 1f else 0f),
                    enabled = buttonShow,
                    onClick = {
                        scope.launch {
                            buttonShow = false
                            viewModel.makeOrder()
                            delay(4000)
                            buttonShow = true
                            void = true
                            navController.navigate(Destinations.History.route){
                                popUpTo("restaurants")
                                launchSingleTop = true
                            }

                        }
                    },
                    //colors = ButtonDefaults.buttonColors()
                ) {
                    Text(text = "Realizar pedido")
                }

            }



            LazyColumn(
                contentPadding = PaddingValues(
                    top = 12.dp,
                    bottom = 100.dp
                ),

                ) {

                if(state.orderDetails.size != 0) {
                    itemsIndexed(state.orderDetails) { _, item ->

                        ItemOrder(item = item) {
                            state.orderDetails.remove(item)
                            CartViewModel.currentOrderDetails.remove(item)

                            val totalNew = reSum(state.orderDetails)
                            CartViewModel.currentOrderHeader.total = totalNew
                            state.orderHeader.total = totalNew

                            CartViewModel.currentSizeOfOrders.value = CartViewModel.currentOrderDetails.size

                        }
                    }
                }
            }

        }

    }
}





