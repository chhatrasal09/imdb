package com.app.imdb.config.database

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter

@ProvidedTypeConverter
class IntListTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<Int> = try {
        value?.split(",")?.map { it.toInt() } ?: emptyList()
    } catch (exception: Exception) {
        emptyList()
    }

    @TypeConverter
    fun fromList(list: ArrayList<Int>): String = list.joinToString(",")
}