package com.example.educa.database.repository

import android.content.Context
import com.example.educa.database.dao.ConnectionDb
import com.example.educa.model.Like

class LikeRepository(context: Context) {
    var db = ConnectionDb.getDatabase(context).likeDao()

    fun insert(like: Like): Long {
        return db.insert(like)
    }

    fun verifyMatch(likeId: Long): Boolean {
        return db.verify(likeId)
    }

    fun getMatches(loggedUserId: Long): List<Like> {
        return db.getMatches(loggedUserId)
    }

}