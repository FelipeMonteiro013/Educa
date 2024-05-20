package com.example.educa.database.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.educa.model.Like

@Dao
interface LikeDao {
    @Insert
    fun insert(like: Like)
}