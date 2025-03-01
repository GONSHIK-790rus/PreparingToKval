package com.example.preparingtokval.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Flight::class, Favourites::class], version = 1)
abstract class UsersDataBase: RoomDatabase() {
    abstract fun userDao(): IUserDAO

    companion object {
        @Volatile
        private var _instance: UsersDataBase? = null

        fun getDataBase(context: Context): UsersDataBase {
            val tempInstance = _instance

            if (tempInstance != null)
                return tempInstance

            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UsersDataBase::class.java,
                    "users"
                ).build()

                _instance = instance

                return instance
            }
        }
    }
}