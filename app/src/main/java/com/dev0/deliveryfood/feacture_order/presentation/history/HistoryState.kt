package com.dev0.deliveryfood.feacture_order.presentation.history

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.dev0.deliveryfood.feacture_order.domain.model.OrderHeader

data class HistoryState(
    var historyList: SnapshotStateList<OrderHeader> = mutableStateListOf()
)