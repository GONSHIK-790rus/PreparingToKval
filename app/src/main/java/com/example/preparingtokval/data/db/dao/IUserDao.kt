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
           "AND password = :password " +
           "LIMIT 1")
    suspend fun getUser(login: String, password: String): User?

    @Query("SELECT nickname FROM users " +
           "WHERE nickname = :nickName ")
    suspend fun getNickName(nickName: String): String?

    @Update
    suspend fun updateUser(user: User)
}
