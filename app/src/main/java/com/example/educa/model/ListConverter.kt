package com.example.educa.model

import androidx.room.TypeConverter

class ListConverter {
    @TypeConverter
    fun fromString(value: String): List<Int> {
        return value.split(",").map { it.toInt() }
    }

    @TypeConverter
    fun toString(value: List<Int>): String {
        return value.joinToString(",")
    }
}