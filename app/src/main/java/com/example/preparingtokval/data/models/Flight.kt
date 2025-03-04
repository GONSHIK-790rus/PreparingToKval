package com.example.preparingtokval.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "flights")
data class Flight(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "search_token")
    @SerializedName("searchToken")
    val searchToken: String,

    @ColumnInfo(name = "start_city")
    @SerializedName("startCity")
    val startCity: String,
    @ColumnInfo(name = "start_city_code")
    @SerializedName("startCityCode")
    val startCityCode: String,

    @ColumnInfo(name = "end_city")
    @SerializedName("endCity")
    val endCity: String,
    @ColumnInfo(name = "end_city_code")
    @SerializedName("endCityCode")
    val endCityCode: String,

    @ColumnInfo(name = "start_date")
    @SerializedName("startDate")
    val startDate: String,
    @ColumnInfo(name = "end_date")
    @SerializedName("endDate")
    val endDate: String,

    @ColumnInfo(name = "price")
    @SerializedName("price")
    val price: Int
)
