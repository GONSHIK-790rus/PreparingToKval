package com.example.preparingtokval.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.preparingtokval.data.models.Favourites
import com.example.preparingtokval.data.models.Flight

@Dao
interface IFavouriteDao {
    @Query("SELECT * FROM flights " +
           "INNER JOIN favourites ON flights.search_token = favourites.flight_search_token " +
           "WHERE favourites.user_id = :userId")
    suspend fun getCurrentUserFavourites(userId: Int): List<Flight>

    @Query("SELECT flight_search_token FROM favourites " +
           "WHERE flight_search_token = :flightSearchToken AND user_id = :userId")
    suspend fun getFavouriteID(flightSearchToken: String, userId: Int): String?

    @Query("INSERT INTO favourites (user_id, flight_search_token) " +
           "VALUES (:userId, :flightSearchToken)")
    suspend fun addToFavourite(userId: Int, flightSearchToken: String)

    @Query("DELETE FROM favourites " +
           "WHERE user_id = :userId AND flight_search_token = :flightSearchToken")
    suspend fun removeFromFavourite(userId: Int, flightSearchToken: String)
}
