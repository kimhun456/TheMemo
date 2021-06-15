package com.github.kimhun456.memoapplication.data.source.local

import com.github.kimhun456.memoapplication.domain.entity.user.User
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class UserDaoImpl @Inject constructor() : UserDao {

    override fun insertUser(user: User): Completable {
        return Completable.complete()
    }
}