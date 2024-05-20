package com.example.educa.database.repository

import android.content.Context
import com.example.educa.database.dao.ConnectionDb
import com.example.educa.model.Experience
import com.example.educa.model.Interest
import com.example.educa.model.Skill

class ExperienceRepository(context: Context){
    var db = ConnectionDb.getDatabase(context).experienceDao()

    fun create(experience: Experience) {
        return db.create(experience)
    }

    fun listExperience(): List<Experience>{
        return db.listExperience()
    }

    fun getExperience(id: Long) : Experience{
        return db.getExperience(id)
    }
}