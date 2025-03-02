package com.example.preparingtokval.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.preparingtokval.data.models.Favourites
import com.example.preparingtokval.data.models.Flight

@Dao
interface IFavouriteDao {
    @Query("SELECT * FROM flights " +
            "INNER JOIN favourites ON flights.searchToken = favourites.flightSearchToken " +
            "WHERE favourites.userId = :userId")
    suspend fun getCurrentUserFavourites(userId: Int): List<Flight>

    @Insert
    suspend fun addToFavourite(favourite: Favourites)

    @Query("DELETE FROM favourites " +
           "WHERE userId = :userId AND flightSearchToken = :token")
    suspend fun removeFromFavourite(userId: Int, token: String)
}
