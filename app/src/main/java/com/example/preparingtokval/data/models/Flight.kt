package com.example.preparingtokval.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "flights")
data class Flight(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "search_token")
    val searchToken: String,

    @ColumnInfo(name = "start_city")
    val startCity: String,
    @ColumnInfo(name = "start_city_code")
    val startCityCode: String,

    @ColumnInfo(name = "end_city")
    val endCity: String,
    @ColumnInfo(name = "end_city_code")
    val endCityCode: String,

    @ColumnInfo(name = "start_date")
    val startDate: String,
    @ColumnInfo(name = "end_date")
    val endDate: String,

    @ColumnInfo(name = "price")
    val price: Int
)
