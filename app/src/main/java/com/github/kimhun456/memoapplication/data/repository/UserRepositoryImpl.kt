package com.github.kimhun456.memoapplication.data.repository

import com.github.kimhun456.memoapplication.data.source.local.UserDao
import com.github.kimhun456.memoapplication.domain.entity.user.User
import com.github.kimhun456.memoapplication.domain.repository.UserRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDao: UserDao
) : UserRepository {

    override fun createUser(user: User): Completable = userDao.insertUser(user)

    override fun deleteUser(user: User): Completable {
        TODO("Not yet implemented")
    }

    override fun modifyUser(user: User): Completable {
        TODO("Not yet implemented")
    }
}