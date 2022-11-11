package com.example.room.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Users(
    @ColumnInfo(name = "name")val nome : String,
    @ColumnInfo(name = "idade")val idade : Int,
    @PrimaryKey(autoGenerate = true)val id : Int = 0
)
