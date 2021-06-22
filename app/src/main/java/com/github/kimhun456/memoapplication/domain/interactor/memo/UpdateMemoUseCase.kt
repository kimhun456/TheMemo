package com.github.kimhun456.memoapplication.domain.interactor.memo

import com.github.kimhun456.memoapplication.domain.entity.Memo
import com.github.kimhun456.memoapplication.domain.repository.MemoRepository
import io.reactivex.rxjava3.core.Completable
import javax.inject.Inject

class UpdateMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) {
    fun updateMemo(memo: Memo): Completable = memoRepository.modifyMemo(memo)
}