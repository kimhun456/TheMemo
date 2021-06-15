package com.github.kimhun456.memoapplication.data.repository

import com.github.kimhun456.memoapplication.data.mapper.Mapper
import com.github.kimhun456.memoapplication.data.source.local.MemoDao
import com.github.kimhun456.memoapplication.domain.entity.Memo
import com.github.kimhun456.memoapplication.domain.repository.MemoRepository
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import timber.log.Timber
import javax.inject.Inject

class MemoRepositoryImpl @Inject constructor(
    private val memoDao: MemoDao
) : MemoRepository {

    override fun createMemo(memo: Memo): Completable {
        Timber.d("createMemo() :$memo")
        return memoDao.addMemo(Mapper.mapToMemoEntity(memo))
    }

    override fun modifyMemo(memo: Memo): Completable {
        Timber.d("modifyMemo() :$memo")
        return memoDao.updateMemo(Mapper.mapToMemoEntity(memo))
    }

    override fun deleteMemo(memo: Memo): Completable {
        Timber.d("deleteMemo() :$memo")
        return memoDao.removeMemo(Mapper.mapToMemoEntity(memo))
    }

    override fun loadMemos(): Flowable<List<Memo>> {
        return memoDao.getAllMemos().map {
            it.map { memoEntity -> Mapper.mapToMemo(memoEntity) }
        }
    }
}