package com.example.educa.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_interest")
data class Interest(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var title: String = ""
)
