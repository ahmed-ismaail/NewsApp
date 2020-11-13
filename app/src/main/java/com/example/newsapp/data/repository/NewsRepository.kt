package com.example.newsapp.data.repository

import com.example.newsapp.data.db.ArticleDatabase
import com.example.newsapp.data.remote.RetrofitClient

class NewsRepository(
    val db: ArticleDatabase
) {

    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
            RetrofitClient.api.getBreakingNews(countryCode, pageNumber)

}