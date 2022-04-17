package com.example.cbcnewsapi.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.cbcnewsapi.data.local.NewsDao
import com.example.cbcnewsapi.data.local.NewsDatabase
import com.example.cbcnewsapi.domain.model.Images
import com.example.cbcnewsapi.domain.model.News
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsDatabaseTest : TestCase() {

    val TAG = "TAG"

    private lateinit var db: NewsDatabase
    private lateinit var dao: NewsDao

    @Before
    public override fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).build()
        dao = db.newsDao()
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadNews() = runBlocking {

        val testImage = Images("testImage")
        val testNewsOne = News("testTitle", "video", testImage, "20220202")
        val listOfTestNews = mutableListOf(testNewsOne)

        Log.d(TAG, "writeAndReadNews: listOfTest ---> $listOfTestNews")
        dao.insertNews(listOfTestNews)

        val lastTwenty = dao.getLastTwentyNews()
        Log.d(TAG, "writeAndReadNews: lastTwenty ---> $lastTwenty")

        assertTrue(lastTwenty == listOfTestNews)
    }
}