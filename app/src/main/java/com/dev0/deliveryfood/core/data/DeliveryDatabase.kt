package com.dev0.deliveryfood.core.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dev0.deliveryfood.feacture_news.data.data_source.NewDao
import com.dev0.deliveryfood.feacture_news.domain.model.New
import com.dev0.deliveryfood.feacture_order.data.data_source.OrderDetailDao
import com.dev0.deliveryfood.feacture_order.data.data_source.OrderHeaderDao
import com.dev0.deliveryfood.feacture_order.domain.model.OrderDetail
import com.dev0.deliveryfood.feacture_order.domain.model.OrderHeader
import com.dev0.deliveryfood.feature_food.data.data_source.FoodDao
import com.dev0.deliveryfood.feature_food.domain.model.Food
import com.dev0.deliveryfood.feature_restaurant.data.data_source.RestaurantDao
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant

@Database(
    entities = [Restaurant::class, Food::class, OrderHeader::class, OrderDetail::class, New::class],
    version = 1
)
abstract class DeliveryDatabase : RoomDatabase() {
    abstract val restaurantDao: RestaurantDao
    abstract val foodDao: FoodDao
    abstract val orderHeaderDao: OrderHeaderDao
    abstract val orderDetailDao: OrderDetailDao
    abstract val newsDao: NewDao
    companion object {
        const val DATABASE_NAME = "delivery_app_db.db"
    }

}