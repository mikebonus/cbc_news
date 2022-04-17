package com.example.cbcnewsapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.cbcnewsapi.domain.model.News
import com.example.cbcnewsapi.utils.Converters

@Database(
    entities = [News::class],
    version = 1
)

@TypeConverters(Converters::class)
abstract class NewsDatabase : RoomDatabase() {

    abstract fun newsDao(): NewsDao

//    companion object {
//        @Volatile
//        private var instance: NewsDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: createDatabase(context).also { instance = it }
//        }
//
//        private fun createDatabase(context: Context) {
//            Room.databaseBuilder(
//                context.applicationContext,
//                NewsDatabase::class.java,
//                "news_db.db"
//            ).build()
//        }
//    }

}