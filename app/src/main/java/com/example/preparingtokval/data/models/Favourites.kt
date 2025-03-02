package com.example.preparingtokval.data.models

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
    val userId: Int,
    val flightSearchToken: String
)
