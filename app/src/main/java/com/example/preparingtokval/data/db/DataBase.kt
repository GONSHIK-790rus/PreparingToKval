package com.example.preparingtokval.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.preparingtokval.data.models.Favourites
import com.example.preparingtokval.data.models.Flight
import com.example.preparingtokval.data.models.User
import com.example.preparingtokval.data.db.dao.IFavouriteDao
import com.example.preparingtokval.data.db.dao.IFlightDao
import com.example.preparingtokval.data.db.dao.IUserDao

@Database(entities = [Flight::class, User::class, Favourites::class], version = 1)
abstract class DataBase : RoomDatabase() {
    abstract fun flightDao(): IFlightDao
    abstract fun userDao(): IUserDao
    abstract fun favouriteDao(): IFavouriteDao

    companion object {
        @Volatile
        private var _dataBase: DataBase? = null

        fun getDataBase(context: Context): DataBase {
            val tempDataBase = _dataBase

            if (tempDataBase != null)
                return tempDataBase

            val dataBase = Room.databaseBuilder(
                context.applicationContext,
                DataBase::class.java,
                "aviaCompany"
            ).build()

            _dataBase = dataBase

            return dataBase
        }
    }
}
