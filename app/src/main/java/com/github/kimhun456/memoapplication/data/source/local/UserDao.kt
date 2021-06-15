package com.github.kimhun456.memoapplication.data.source.local

import com.github.kimhun456.memoapplication.domain.entity.user.User
import io.reactivex.rxjava3.core.Completable

interface UserDao {
    fun insertUser(user: User): Completable
}