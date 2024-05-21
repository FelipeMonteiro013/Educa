package com.example.educa.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.educa.model.AcademicEducation
import com.example.educa.model.Experience
import com.example.educa.model.Interest
import com.example.educa.model.Like
import com.example.educa.model.Skill
import com.example.educa.model.User
import java.util.concurrent.Executors

@Database(
    entities = [User::class, Interest::class, AcademicEducation::class, Skill::class, Experience::class, Like::class],
    version = 4
)
abstract class ConnectionDbBackup : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun interestDao(): InterestDao
    abstract fun academicEducationDao(): AcademicEducationDao
    abstract fun skillDao(): SkillDao

    abstract fun experienceDao(): ExperienceDao

    abstract fun likeDao(): LikeDao


    companion object {

        private lateinit var instance: ConnectionDbBackup
        fun getDatabase(context: Context): ConnectionDbBackup {
            if (!::instance.isInitialized) {
                instance = Room.databaseBuilder(context, ConnectionDbBackup::class.java, "educa_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .addCallback(DatabaseCallback(context))
                    .build()
            }
            return instance
        }

        private class DatabaseCallback(
            private val context: Context
        ) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

            }
        }

    }

}