package com.example.preparingtokval.data.repository

import com.example.preparingtokval.data.db.dao.IUserDao
import com.example.preparingtokval.data.models.User

class UserRepository(private val userDao: IUserDao) {
    suspend fun addUser(user: User) = userDao.addUser(user)
    suspend fun getUser(login: String, password: String) = userDao.getUser(login, password)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
}
