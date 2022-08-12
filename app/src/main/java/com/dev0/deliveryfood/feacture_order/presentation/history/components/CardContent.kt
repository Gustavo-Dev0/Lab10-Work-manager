package com.dev0.deliveryfood.feacture_order.presentation.history.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.dev0.deliveryfood.feacture_order.domain.model.OrderHeader


@OptIn(ExperimentalCoilApi::class)
@Composable
fun CardContent(
    order: OrderHeader
) {
    //var order = Order(date = "weffwe", user = "fwef", id = 45, total = 464.0, status = 1)
    Row (
        Modifier.padding(12.dp)
            ){
        Text(
            text = "Fecha de pedido: " + order.date,
            style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
    }
    Row(
        modifier = Modifier
            .padding(horizontal = 12.dp).padding(top = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Text(
            modifier = Modifier.padding(top = 20.dp),
            text = "Monto: S/. " +order.total,
            style = MaterialTheme.typography.h4
        )

        Icon(
            when (order.status) {
                OrderHeader.SEND -> {
                    Icons.Filled.Send
                }
                OrderHeader.TO_SEND -> {
                    Icons.Filled.Info
                }
                OrderHeader.DELIVERED -> {
                    Icons.Filled.Check
                }
                else -> {
                    Icons.Filled.Error
                }
            },
            modifier = Modifier
                .size(64.dp),
            contentDescription = ""
        )
    }

}