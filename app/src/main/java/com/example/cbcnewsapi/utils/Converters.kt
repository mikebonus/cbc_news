package com.example.cbcnewsapi.utils

import androidx.room.TypeConverter
import com.example.cbcnewsapi.domain.model.Images

class Converters {

    @TypeConverter
    fun fromImage(image: Images): String {
        return image.square_140
    }

    @TypeConverter
    fun toImages(square_140: String): Images {
        return Images(square_140)
    }

}