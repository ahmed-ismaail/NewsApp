package com.example.newsapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.newsapp.data.models.Article

@Dao
interface ArticleDao {
    //onConflict so if you try insert a column already exist replace it
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(article: Article): Long

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<Article>

    @Delete
    suspend fun deleteArticle(article: Article)
}