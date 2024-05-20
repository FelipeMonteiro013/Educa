package com.example.educa.database.dao

import androidx.compose.foundation.pager.PagerSnapDistance
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.educa.model.User
@Dao
interface UserDao {
    @Insert
    fun create(user: User) : Long

    @Update
    fun update(user: User) : Int

    @Delete
    fun delete(user: User) : Int

    @Query("SELECT * FROM tbl_user WHERE id = :id")
    fun getUserById(id: Long)  : User

    @Query("SELECT * FROM tbl_user")
    fun listUsers() : List<User>

    @Query("SELECT * FROM tbl_user")
    fun checkDb() : Boolean

}