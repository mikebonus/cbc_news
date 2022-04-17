package com.example.cbcnewsapi.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

// ROOM can only handle primitive data-types.
// For custom data-types such as 'Images', we need a type-converter..
@Entity(tableName = "news")
data class News(

    @PrimaryKey
    val title: String,
    val type: String,
    val images: Images,
    val readablePublishedAt: String
) {
}