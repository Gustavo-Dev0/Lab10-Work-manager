package com.dev0.deliveryfood.feacture_news.presentation.news

import com.dev0.deliveryfood.feacture_news.domain.model.New

data class NewsState(
    val news: List<New> = emptyList(),
)
