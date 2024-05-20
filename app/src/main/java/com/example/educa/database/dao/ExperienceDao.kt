package com.example.educa.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.educa.model.Experience

@Dao
interface ExperienceDao {

    @Insert
    fun create(experience: Experience)

    @Insert
    fun insertAll(listExperience: List<Experience>)

    @Query("SELECT * FROM tbl_experience")
    fun listExperience(): List<Experience>

    @Query("SELECT * FROM tbl_skill WHERE id = :id")
    fun getExperience(id: Long): Experience

}