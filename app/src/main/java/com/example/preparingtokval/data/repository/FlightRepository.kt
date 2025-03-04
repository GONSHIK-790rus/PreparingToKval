package com.example.preparingtokval.data.repository

import com.example.preparingtokval.data.db.dao.IFlightDao
import com.example.preparingtokval.data.models.Flight

class FlightRepository(private val flightDao: IFlightDao) {
    suspend fun getAllFlights() = flightDao.getAllFlights()
    suspend fun addFlight(flights: List<Flight>) = flightDao.addFlight(flights)
}
