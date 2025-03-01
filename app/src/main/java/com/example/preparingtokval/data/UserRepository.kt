package com.example.preparingtokval.data

class UserRepository(private val userDao: IUserDAO) {
    val readAllData: List<User> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }
}