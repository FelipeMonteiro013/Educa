package com.example.educa.database.repository

import android.content.Context
import com.example.educa.database.dao.ConnectionDb
import com.example.educa.model.Skill

class SkillRepository(context: Context){
    var db = ConnectionDb.getDatabase(context).skillDao()

    fun create(skill: Skill) {
        return db.create(skill)
    }

    fun listSkills(): List<Skill>{
        return db.listSkills()
    }

    fun getSkill(id: Long) : Skill{
        return db.getSkill(id)
    }
}