package com.example.cbcnewsapi.utils.di

import android.app.Application
import androidx.room.Room
import com.example.cbcnewsapi.data.api.NewsApi
import com.example.cbcnewsapi.data.local.NewsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    // MODULE #1: retrofit-object
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(NewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    // MODULE #2: restaurant-object
    @Provides
    @Singleton
    fun provideRestaurantApi(retrofit: Retrofit): NewsApi =
        retrofit.create(NewsApi::class.java)


    // MODULE #3: d/b instance
    @Provides
    @Singleton
    fun provideDatabase(app: Application): NewsDatabase =
        Room.databaseBuilder(app, NewsDatabase::class.java, "news database")
            .build()

}