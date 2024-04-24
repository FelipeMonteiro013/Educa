package com.example.educa.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.educa.model.User
@Database(entities = [User::class], version = 1)
abstract class UserDb : RoomDatabase() {

    abstract fun userDao() : UserDao

    companion object {

        private lateinit var instance : UserDb
        fun getDatabase(context: Context) : UserDb{
            if (!::instance.isInitialized){
                instance = Room.databaseBuilder(context, UserDb::class.java, "educa_db")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }

}