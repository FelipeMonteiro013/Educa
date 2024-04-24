package com.example.educa.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "tbl_user")
@TypeConverters(ListConverter::class)
data class User(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String = "",
    var email: String = "",
    var password: String = "",

    @ColumnInfo(name= "dt_nasc")
    var dtNasc: String = "",
    var gender: String = "",

    @ColumnInfo(name= "account_type")
    var accountType: Int = 0,
    var distance: Int = 0,

    var interest: List<Int>,

    @ColumnInfo(name= "academic_education")
    var academicEducation: List<Int>,
    var skills: List<Int>,
    var experiences: List<Int>,

    @ColumnInfo(name= "user_photo")
    var userPhoto: String = ""

)
