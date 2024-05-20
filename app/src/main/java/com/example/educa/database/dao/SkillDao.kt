package com.example.educa.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.educa.model.Skill

@Dao
interface SkillDao {

    @Insert
    fun create(skill: Skill)

    @Insert
    fun insertAll(listSkills: List<Skill>)

    @Query("SELECT * FROM tbl_skill")
    fun listSkills(): List<Skill>

    @Query("SELECT * FROM tbl_skill WHERE id = :id")
    fun getSkill(id: Long): Skill

}