package com.example.educa.database.repository

import android.content.Context
import com.example.educa.database.dao.UserDb
import com.example.educa.model.User

class UserRepository(context: Context) {
    var db = UserDb.getDatabase(context).userDao()

    fun create(user: User): Long {
        return db.create(user = user)
    }

//    TODO Mover esse trecho para um arquivo separado
    fun listUsers(): List<User> {
        return db.listUsers()
    }

}