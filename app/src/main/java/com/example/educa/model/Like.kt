package com.example.educa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_likes")
data class Like(
    @PrimaryKey(autoGenerate = true)
    var id: Long,
    var loggedUserId: Long?,
    var loggedUserLike: Boolean?,
    var userId: Long,
    var userLike: Boolean,
)
