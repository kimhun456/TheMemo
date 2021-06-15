package com.github.kimhun456.memoapplication.domain.repository

import com.github.kimhun456.memoapplication.domain.entity.user.User
import io.reactivex.rxjava3.core.Completable

interface UserRepository {

    fun createUser(user: User): Completable
    fun deleteUser(user: User): Completable
    fun modifyUser(user: User): Completable
}
