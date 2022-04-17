package com.example.cbcnewsapi.data.pagingSource

import androidx.paging.PagingSource
import com.example.cbcnewsapi.data.api.NewsApi
import com.example.cbcnewsapi.domain.model.News
import retrofit2.HttpException
import java.io.IOException

private const val NEWS_API_STARTING_INDEX = 1

class VideoPagingSource(
    private val newsApi: NewsApi
) : PagingSource<Int, News>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, News> {

        val position = params.key ?: NEWS_API_STARTING_INDEX           // current-position

        return try {
            val response =
                newsApi.getNewsVideo()
            LoadResult.Page(
                data = response,
                prevKey = if (position == NEWS_API_STARTING_INDEX) null else position - 1,
                nextKey = if (response.isEmpty()) null else position + 1
            )

        } catch (exception: IOException) {
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}