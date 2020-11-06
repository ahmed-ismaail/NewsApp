package com.example.newsapp.data

import com.example.newsapp.data.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)