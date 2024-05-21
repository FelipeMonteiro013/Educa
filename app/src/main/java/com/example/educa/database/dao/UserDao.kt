package com.example.educa.database.dao

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

    @Insert
    fun insertAll(listUsers: List<User>)

    @Update
    fun update(user: User) : Int

    @Delete
    fun delete(user: User) : Int

    @Query("SELECT * FROM tbl_user WHERE id = :id")
    fun getUserById(id: Long)  : User

    @Query("SELECT * FROM tbl_user")
    fun listUsers() : List<User>

    @Query("SELECT * FROM tbl_user WHERE id != :loggedUserId")
    fun listUsersDiferentLoggedUser(loggedUserId: Long) : List<User>

    @Query("SELECT * FROM tbl_user WHERE email = :email AND password = :password")
    fun login(email: String, password: String) : Long

    @Query("SELECT * FROM tbl_user WHERE id != :loggedUserId AND account_type = :accountType")
    fun listByAccountType(loggedUserId: Long, accountType : Int  ) : List<User>

    @Query("""
        SELECT * FROM tbl_user
        WHERE (:accountType IS NULL OR account_type = :accountType) AND id != :loggedUserId AND (:distance IS NULL OR distance <= :distance)
    """)
    fun testFilter(loggedUserId: Long, accountType : Int?, distance: Int?  ) : List<User>

}