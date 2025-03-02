package com.example.preparingtokval.data.repository

import com.example.preparingtokval.data.db.dao.IFlightDao

class FlightRepository(private val flightDao: IFlightDao) {
    suspend fun getAllFlights() = flightDao.getAllFlights()
}
