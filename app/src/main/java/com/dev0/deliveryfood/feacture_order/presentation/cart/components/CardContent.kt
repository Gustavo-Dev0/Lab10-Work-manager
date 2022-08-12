package com.dev0.deliveryfood.feacture_order.presentation.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.annotation.ExperimentalCoilApi
import com.dev0.deliveryfood.feacture_order.domain.model.OrderDetail

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CardContent(
    orderdetail: OrderDetail,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .padding(12.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(){
            Text(
                text = orderdetail.food.name,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                )
            )
            Text(
                text = orderdetail.food.name,
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 12.sp
                )
            )
            Row( /*modifier = Modifier
                .fillMaxWidth()
                .padding()
                .padding(vertical = 10.dp),*/
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Unidad: S/. "+orderdetail.food.price.toString(),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 12.sp)
                )
                Spacer(Modifier.width(30.dp))
                Text(
                    text = "Cantidad: "+orderdetail.quantity.toString(),
                    style = MaterialTheme.typography.body1.copy(
                        fontSize = 12.sp)
                )
            }
            Text(
                text = "Total: S/. "+orderdetail.total.toString(),
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 16.sp)
            )
        }
        Column(){
            IconButton(
                onClick = onClick
            ) {
                Icon(modifier = Modifier.size(64.dp), imageVector = Icons.Filled.Delete, contentDescription = null, tint = Color.Red)
            }
        }
    }

}