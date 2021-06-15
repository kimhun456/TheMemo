package com.github.kimhun456.memoapplication.data.source.local

import android.content.Context
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [DataBaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class LocalSourceModule {

    @Binds
    abstract fun provideUserDao(userDaoImpl: UserDaoImpl): UserDao
}

@Module
@InstallIn(SingletonComponent::class)
internal object DataBaseModule {

    @Provides
    fun providesMemoDao(localDataBase: LocalDataBase): MemoDao {
        return localDataBase.memoDao()
    }

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext appContext: Context): LocalDataBase {
        return Room.databaseBuilder(
            appContext,
            LocalDataBase::class.java,
            "memo"
        ).build()
    }
}
