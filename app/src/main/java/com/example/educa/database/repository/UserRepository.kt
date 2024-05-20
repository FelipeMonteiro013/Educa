package com.example.educa.database.repository

import android.content.Context
import com.example.educa.database.dao.ConnectionDb
import com.example.educa.model.User

class UserRepository(context: Context) {
    var db = ConnectionDb.getDatabase(context).userDao()

    fun create(user: User): Long {
        return db.create(user = user)
    }

//    TODO Mover esse trecho para um arquivo separado
    fun listUsers(): List<User> {
        return db.listUsers()
    }

    fun getUserById(id: Long): User {
     return db.getUserById(id)
    }

    fun checkDb() : Boolean {
        return db.checkDb()
    }

    fun login(email: String, password: String): Long{
        return db.login(email, password);
    }


}