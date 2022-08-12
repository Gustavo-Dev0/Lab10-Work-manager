package com.dev0.deliveryfood.feacture_order.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.dev0.deliveryfood.feature_food.domain.model.Food

@Entity(tableName = "orders_detail")
data class OrderDetail(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,

    @ColumnInfo(name = "order_id") var orderId: Int? = null,
    var restaurant: Int,
    //var restaurantName: String,
    @ColumnInfo(name = "food_id")var foodId: Int,
    var quantity: Int,
    var total: Double
) {
    @Ignore lateinit var food: Food
}
