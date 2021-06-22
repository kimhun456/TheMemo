package com.github.kimhun456.memoapplication.domain.repository

import com.github.kimhun456.memoapplication.domain.entity.Memo
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface MemoRepository {
    fun createEmptyMemo(): Single<Long>
    fun createMemo(memo: Memo): Completable
    fun modifyMemo(memo: Memo): Completable
    fun deleteMemo(memo: Memo): Completable
    fun loadMemo(id: Long): Single<Memo>
    fun loadMemos(): Flowable<List<Memo>>
}
