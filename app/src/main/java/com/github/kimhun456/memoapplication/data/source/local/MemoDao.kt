package com.github.kimhun456.memoapplication.data.source.local

import com.github.kimhun456.memoapplication.data.entity.MemoEntity
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface MemoDao {
    fun addMemo(memo: MemoEntity): Completable
    fun removeMemo(memo: MemoEntity): Completable
    fun updateMemo(memo: MemoEntity): Completable
    fun getMemo(id: Long): Single<MemoEntity>
    fun getAllMemos(): Flowable<List<MemoEntity>>
}