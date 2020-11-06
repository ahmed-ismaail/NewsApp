package com.example.newsapp.data.remote

import com.example.newsapp.data.models.NewsResponse
import com.example.newsapp.util.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

//http://newsapi.org/v2/everything?q=bitcoin&from=2020-10-06&sortBy=publishedAt&apiKey=80c2e0be242c4772a3fb66327464e527
interface NewsApi {
    @GET("v2/top_headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("page")
        pagenumber : Int = 1,//to make the request get only the first page (first 20 articles)
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("country")
        searchQuery: String,
        @Query("page")
        pagenumber : Int = 1,//to make the request get only the first page (first 20 articles)
        @Query("apiKey")
        apiKey: String = Constants.API_KEY
    ): Response<NewsResponse>
}