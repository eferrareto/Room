package com.example.room.database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Users::class], version = 1 )
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao() : UsersDao

    companion object{
        var INSTANCE: AppDatabase? = null

        fun getDatabase(context : Context): AppDatabase {
            @Synchronized
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "database.db"
                ).build()
            } else {
                return INSTANCE as AppDatabase
            }
            return INSTANCE as AppDatabase
        }
    }
}