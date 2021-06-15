package com.github.kimhun456.memoapplication.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.github.kimhun456.memoapplication.data.entity.MemoEntity

@Database(entities = [MemoEntity::class], version = 1)
abstract class LocalDataBase : RoomDatabase() {
    abstract fun memoDao(): MemoDaoImpl
}
