package com.example.preparingtokval.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.preparingtokval.data.models.Flight

@Dao
interface IFlightDao {
    @Query("SELECT * FROM flights")
    suspend fun getAllFlights(): List<Flight>
}
