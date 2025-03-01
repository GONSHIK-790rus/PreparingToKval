package com.example.preparingtokval.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(tableName = "flights")
data class Flight(
    @PrimaryKey(autoGenerate = false)
    val searchToken: String,

    val startCity: String,
    val startCityCode: String,

    val endCity: String,
    val endCityCode: String,

    val startDate: Instant,
    val endDate: Instant,

    val price: Int
)
