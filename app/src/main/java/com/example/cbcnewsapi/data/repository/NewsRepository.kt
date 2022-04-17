package com.example.cbcnewsapi.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import androidx.room.withTransaction
import com.codinginflow.simplecachingexample.util.networkBoundResource
import com.example.cbcnewsapi.data.api.NewsApi
import com.example.cbcnewsapi.data.local.NewsDatabase
import com.example.cbcnewsapi.data.pagingSource.NewsPagingSource
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsApi,
    private val db: NewsDatabase
) {
    private val newsDao = db.newsDao()

    // PAGINATION
    fun getNewsPageResult() =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 100,
                enablePlaceholders = false      // Paging can enable placeholders for objects that have not been loaded yet, but NOT in this case..
            ),
            pagingSourceFactory = {
                NewsPagingSource(api)
            }
        ).liveData


    // CACHE
    // we will only use 'true' for 4th param.. (true by default)..
    fun getNews() = networkBoundResource(
        query = {
            newsDao.getAllNews()
        },

        fetch = {
//            delay(100)
            api.getNews()
        },

        saveFetchResult = { news ->
            // all operations or nothing..
            db.withTransaction {
                newsDao.deleteAllNews()
                newsDao.insertNews(news)
            }
        }
    )
}