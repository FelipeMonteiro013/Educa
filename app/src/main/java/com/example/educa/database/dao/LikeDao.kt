package com.example.educa.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.educa.model.Like

@Dao
interface LikeDao {
    @Insert
    fun insert(like: Like)

    @Query("SELECT * FROM tbl_likes WHERE userId = :userId")
    fun getPossibleLike(userId: Long): Like

    @Update
    fun like(like: Like)
}