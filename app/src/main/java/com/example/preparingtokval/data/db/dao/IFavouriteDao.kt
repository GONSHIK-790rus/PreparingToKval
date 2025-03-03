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

    @Insert
    suspend fun addToFavourite(favourite: Favourites)

    @Query("DELETE FROM favourites " +
           "WHERE user_id = :userId AND flight_search_token = :token")
    suspend fun removeFromFavourite(userId: Int, token: String)
}
