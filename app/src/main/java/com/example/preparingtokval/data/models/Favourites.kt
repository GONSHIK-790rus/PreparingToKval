package com.example.preparingtokval.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index

@Entity(tableName = "favourites",
    primaryKeys = ["userId", "flightSearchToken"],
    foreignKeys = [
        ForeignKey(
            entity = User::class,
            parentColumns = ["id"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Flight::class,
            parentColumns = ["searchToken"],
            childColumns = ["flightSearchToken"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [
        Index(value = ["userId"]),
        Index(value = ["flightSearchToken"])
    ]
)
data class Favourites(
    @ColumnInfo(name = "user_id")
    val userId: Int,
    @ColumnInfo(name = "flight_search_token")
    val flightSearchToken: String
)
