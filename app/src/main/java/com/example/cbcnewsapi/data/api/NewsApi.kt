package com.example.cbcnewsapi.data.api

import com.example.cbcnewsapi.domain.model.News
import retrofit2.http.GET

interface NewsApi {

    companion object {
        const val BASE_URL = "https://www.cbc.ca/aggregate_api/"
    }

    @GET("v1/items?lineupSlug=news")            // or "v1/items"
    suspend fun getNews(): List<News>


    @GET("v1/items?type=video")                  // 'video'
    suspend fun getNewsVideo(): List<News>

}