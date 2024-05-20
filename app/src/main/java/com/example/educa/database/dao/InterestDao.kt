package com.example.educa.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.educa.model.Interest

@Dao
interface InterestDao {
    @Insert
    fun create(insert: Interest)

    @Insert
    fun insertAll(interests: List<Interest>)

    @Query("SELECT * FROM tbl_interest")
    fun listInterests(): List<Interest>

    @Query("SELECT * FROM tbl_interest WHERE id = :id")
    fun getInterest(id: Int): Interest
}