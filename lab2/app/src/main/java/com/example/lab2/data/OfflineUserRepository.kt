package com.example.lab2.data

import kotlinx.coroutines.flow.Flow

class OfflineUserRepository(private val userDao: UserDao) : UserRepository{
    override suspend fun getAllStream(): Flow<List<User>> = userDao.getAll()

    override fun findByEmailStream(email: String): Flow<User?> = userDao.findByEmail(email)

    override suspend fun insertItem(user: User) = userDao.insert(user)

    override suspend fun deleteItem(user: User) = userDao.delete(user)

    override suspend fun updateItem(user: User) = userDao.update(user)
}