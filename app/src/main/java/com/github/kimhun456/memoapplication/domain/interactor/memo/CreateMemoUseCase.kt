package com.github.kimhun456.memoapplication.domain.interactor.memo

import com.github.kimhun456.memoapplication.domain.entity.Memo
import com.github.kimhun456.memoapplication.domain.repository.MemoRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class CreateMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) {
    fun createMemo(memo: Memo): Completable = memoRepository.createMemo(memo)
}