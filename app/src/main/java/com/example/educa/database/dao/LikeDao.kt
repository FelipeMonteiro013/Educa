package com.example.educa.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.educa.model.Like

@Dao
interface LikeDao {
    @Insert
    fun insert(like: Like): Long

    @Query("SELECT * FROM tbl_likes WHERE userId = :userId")
    fun getPossibleLike(userId: Long): Like

    @Update
    fun like(like: Like)

    @Query("SELECT (CASE WHEN loggedUserLike = 1 AND userLike = 1 THEN 1 ELSE 0 END ) FROM tbl_likes WHERE id = :id")
    fun verify(id: Long): Boolean
}