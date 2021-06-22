package com.github.kimhun456.memoapplication.domain.interactor.memo

import com.github.kimhun456.memoapplication.domain.entity.Memo
import com.github.kimhun456.memoapplication.domain.repository.MemoRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class LoadMemoUseCase @Inject constructor(
    private val memoRepository: MemoRepository
) {
    fun loadMemo(id: Long): Single<Memo> = memoRepository.loadMemo(id)
}