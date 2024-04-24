package com.example.educa.database.repository

import android.content.Context
import com.example.educa.database.dao.UserDb
import com.example.educa.model.User

class UserRepository(context: Context) {
    var db = UserDb.getDatabase(context).userDao()

    fun create(user: User): Long {
        return db.create(user = user)
    }

    fun update(user: User): Int {
        return db.update(user = user)
    }

    fun delete(user: User): Int {
        return db.delete(user = user)
    }

    fun getUserById(id: Long): User {
        return db.getUserById(id = id)
    }

    fun listUsers(): List<User> {
        return db.listUsers()
    }


}