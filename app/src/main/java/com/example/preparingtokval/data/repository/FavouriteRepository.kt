package com.example.preparingtokval.data.repository

import com.example.preparingtokval.data.db.dao.IFavouriteDao
import com.example.preparingtokval.data.models.Favourites

class FavouriteRepository(private val favouriteDao: IFavouriteDao) {
    suspend fun getCurrentUserFavourites(userId: Int) = favouriteDao.getCurrentUserFavourites(userId)
    suspend fun addToFavourite(fav: Favourites) = favouriteDao.addToFavourite(fav)
    suspend fun removeFromFavourite(userId: Int, token: String) = favouriteDao.removeFromFavourite(userId, token)
}
