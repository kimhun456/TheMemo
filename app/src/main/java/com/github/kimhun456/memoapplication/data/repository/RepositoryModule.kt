package com.github.kimhun456.memoapplication.data.repository

import com.github.kimhun456.memoapplication.domain.repository.MemoRepository
import com.github.kimhun456.memoapplication.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindsUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository

    @Binds
    abstract fun bindsMemoRepository(impl: MemoRepositoryImpl): MemoRepository
}