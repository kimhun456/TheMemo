package com.github.kimhun456.memoapplication.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.github.kimhun456.memoapplication.data.entity.MemoEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
abstract class MemoDaoImpl : MemoDao {

    @Insert
    abstract override fun addMemo(memo: MemoEntity): Completable

    @Delete
    abstract override fun removeMemo(memo: MemoEntity): Completable

    @Update
    abstract override fun updateMemo(memo: MemoEntity): Completable

    @Query("SELECT * FROM memo WHERE id = :id")
    abstract override fun getMemo(id: Long): Single<MemoEntity>

    @Query("SELECT * FROM memo")
    abstract override fun getAllMemos(): Flowable<List<MemoEntity>>
}