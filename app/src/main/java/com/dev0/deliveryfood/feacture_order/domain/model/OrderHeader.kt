package com.dev0.deliveryfood.feacture_order.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderHeader (

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val date: String = "14/07/2022",
    val status: Int = TO_SEND,
    var total: Double = 0.0,
    var user: String = ""
) {
    companion object {
        const val TO_SEND = 1
        const val SEND = 2
        const val DELIVERED = 3
    }
}