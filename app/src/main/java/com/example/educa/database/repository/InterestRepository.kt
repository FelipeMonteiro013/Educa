package com.example.educa.database.repository

import android.content.Context
import com.example.educa.database.dao.ConnectionDb
import com.example.educa.model.Interest

class InterestRepository(context: Context){
    var db = ConnectionDb.getDatabase(context).interestDao()

    fun create(interest: Interest) {
        return db.create(interest)
    }

    fun listInterests(): List<Interest>{
        return db.listInterests()
    }

    fun getInterest(id: Int) : Interest{
        return db.getInterest(id)
    }
}