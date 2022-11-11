package com.example.room.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDao {

    @Insert
    fun insertUsers(users: Users)

    @Query("SELECT * FROM Users")
    fun getAllUsers() : List<Users>
}