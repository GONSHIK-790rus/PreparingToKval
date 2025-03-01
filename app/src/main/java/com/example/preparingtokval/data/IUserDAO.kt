package com.example.preparingtokval.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface IUserDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM users " +
           "ORDER BY id ASC")
    fun readAllData(): List<User>

    @Query("SELECT * FROM users "
    +      "WHERE (login = :loginOrEMail OR nickName = :loginOrEMail) "
    +      "AND password = :password")
    fun getCurrentUser(loginOrEMail: String, password: String): User

    @Update
    fun updateUser(user: User)
}
