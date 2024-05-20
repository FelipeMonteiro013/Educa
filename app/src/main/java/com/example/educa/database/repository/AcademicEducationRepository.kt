package com.example.educa.database.repository

import android.content.Context
import com.example.educa.database.dao.ConnectionDb
import com.example.educa.model.AcademicEducation

class AcademicEducationRepository(context: Context){
    var db = ConnectionDb.getDatabase(context).academicEducationDao()

    fun create(academicEducation: AcademicEducation) {
        return db.create(academicEducation)
    }

    fun listAcademicEducation(): List<AcademicEducation>{
        return db.listAcademicEducation()
    }

}