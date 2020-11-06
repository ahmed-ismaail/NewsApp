package com.example.newsapp.data.db

import android.content.Context
import androidx.room.*
import com.example.newsapp.data.models.Article

@Database(entities = [Article::class], version = 1)
@TypeConverters(Converter::class)
abstract class ArticleDatabase : RoomDatabase() {

    abstract fun getArticleDao(): ArticleDao

    companion object {
        @Volatile //to prevent having multiple instances of the database opened at the same time.
        private var Instance: ArticleDatabase? = null

        fun getDatabaseInstance(context: Context): ArticleDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return Instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ArticleDatabase::class.java,
                    "article_db.db"
                ).build()
                Instance = instance

                //return instance
                instance
            }
        }
//        private fun createDatabase(context: Context){
//            Room.databaseBuilder(
//                context.applicationContext,
//                ArticleDatabase::class.java,
//                "article_db.db"
//                ).build()
//        }
    }
}