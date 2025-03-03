package com.example.preparingtokval.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.preparingtokval.data.models.User

@Dao
interface IUserDao {
    @Insert
    suspend fun addUser(user: User)

    @Query("SELECT * FROM users " +
           "WHERE (nickname = :login OR login = :login) " +
           "AND password = :password")
    suspend fun getUser(login: String, password: String): User?

    @Update
    suspend fun updateUser(user: User)
}
