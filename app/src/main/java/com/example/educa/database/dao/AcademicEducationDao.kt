package com.example.educa.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.educa.model.AcademicEducation
import com.example.educa.model.Interest

@Dao
interface AcademicEducationDao {

    @Insert
    fun create(academicEducation: AcademicEducation)

    @Insert
    fun insertAll(listAcademicEducation: List<AcademicEducation>)

    @Query("SELECT * FROM tbl_academic_education")
    fun listAcademicEducation(): List<AcademicEducation>

    @Query("SELECT * FROM tbl_academic_education WHERE id = :id")
    fun getAcademicEducation(id: Long): AcademicEducation

}