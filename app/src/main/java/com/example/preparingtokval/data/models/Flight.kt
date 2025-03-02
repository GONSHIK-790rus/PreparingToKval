package com.example.preparingtokval.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flights")
data class Flight(
    @PrimaryKey(autoGenerate = false)
    val searchToken: String,

    val startCity: String,
    val startCityCode: String,

    val endCity: String,
    val endCityCode: String,

    val startDate: String,
    val endDate: String,

    val price: Int
)
