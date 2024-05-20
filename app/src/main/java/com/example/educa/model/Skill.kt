package com.example.educa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_skill")
data class Skill(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var title: String = ""
)
