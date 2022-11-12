package com.example.room.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.DeleteTable
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDao {

    @Insert
    fun insertUsers(users: Users)

    @Query("SELECT * FROM Users")
    fun getAllUsers() : List<Users>

    @Query("DELETE FROM Users")
    fun deleteAll()

    @Update
    fun updateUser(users: Users)
}