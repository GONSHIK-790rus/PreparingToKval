package com.example.preparingtokval.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "favourites",
    primaryKeys = ["user_id", "flight_search_token"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["user_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Flight::class,
            parentColumns = ["search_token"],
            childColumns = ["flight_search_token"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["user_id"]),
        Index(value = ["flight_search_token"])
    ]
)
data class Favourites(
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "flight_search_token")
    val flightSearchToken: String
)
