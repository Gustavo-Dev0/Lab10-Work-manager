package com.dev0.deliveryfood.feacture_news.presentation.news

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.dev0.deliveryfood.feacture_news.domain.repository.NewRepository
import com.dev0.deliveryfood.feature_restaurant.domain.model.Restaurant
import com.dev0.deliveryfood.feature_restaurant.domain.repository.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val repository: NewRepository
    ): ViewModel() {


    private val _state = mutableStateOf(NewsState())
    val state: State<NewsState> = _state

    init {
        getNews()
    }

    private fun getNews() {
        viewModelScope.launch {
            repository.getNews().collect { list->
                _state.value = state.value.copy(
                    news = list
                )
            }
        }
    }

}