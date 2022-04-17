package com.example.cbcnewsapi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cbcnewsapi.domain.model.News
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    // RETRIEVE
    @Query("SELECT * FROM news WHERE type != 'video'")
    fun getAllNews(): Flow<List<News>>

    // RETRIEVE
    @Query("SELECT * FROM news WHERE type='video'")
    fun getAllNewsVideo(): Flow<List<News>>

    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNews(news: List<News>)

    // DELETE
    @Query("DELETE FROM news")
    suspend fun deleteAllNews()

    // UNIT-TEST (D/B)
    @Query("SELECT * FROM news ORDER BY readablePublishedAt DESC LIMIT 20")
    suspend fun getLastTwentyNews(): List<News>

}