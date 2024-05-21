package com.example.educa.database.repository

import android.content.Context
import com.example.educa.database.dao.ConnectionDb
import com.example.educa.model.Experience

class ExperienceRepository(context: Context){
    var db = ConnectionDb.getDatabase(context).experienceDao()

    fun create(experience: Experience) {
        return db.create(experience)
    }

    fun insertAll(experiences: List<Experience>) {
        return db.insertAll(experiences)
    }

    fun listExperience(): List<Experience>{
        return db.listExperience()
    }

    fun getExperience(id: Long) : Experience{
        return db.getExperience(id)
    }
}