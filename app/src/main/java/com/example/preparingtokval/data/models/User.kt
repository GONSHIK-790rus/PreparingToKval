package com.example.preparingtokval.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val nickName: String,
    val login: String,
    val password: String,
    val age: Int
)
