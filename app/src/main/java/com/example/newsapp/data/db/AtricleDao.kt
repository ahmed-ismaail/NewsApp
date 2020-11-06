package com.example.newsapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapp.data.Article
import retrofit2.http.DELETE

@Dao
interface AtricleDao {
    //onConflict so if you try insert a column already exist replace it
    @Insert(onConflict =  OnConflictStrategy.REPLACE)
    suspend fun upsert(article:Article):Long

    @Query("SELECT * FROM articles")
    fun getAllArticles():LiveData<Article>

    @DELETE
    suspend fun deleteArticle(article: Article)
}