package com.example.educa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_experience")
data class Experience(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var title: String = ""
)
