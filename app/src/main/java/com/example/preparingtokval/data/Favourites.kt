package com.example.preparingtokval.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class Favourites (
    @PrimaryKey(autoGenerate = true)
    val id: Int

)