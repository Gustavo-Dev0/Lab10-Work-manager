package com.dev0.deliveryfood.feacture_order.presentation.history

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev0.deliveryfood.feacture_order.domain.repository.OrderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repository: OrderRepository
)
    : ViewModel() {

    var getHistoryJob: Job? = null

    private val _state = mutableStateOf(HistoryState())
    val state: State<HistoryState> = _state

    init {
        getHistory()
    }


    private fun getHistory() {

        getHistoryJob?.cancel()
        getHistoryJob = repository.getOrders()
            .onEach { history ->
                _state.value.historyList.clear()
                _state.value.historyList.addAll(history)
            }.launchIn(viewModelScope)

    }
}